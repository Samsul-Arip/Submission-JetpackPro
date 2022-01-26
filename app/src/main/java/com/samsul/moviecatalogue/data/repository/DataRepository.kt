package com.samsul.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.samsul.moviecatalogue.data.ApiResponse
import com.samsul.moviecatalogue.data.local.LocalDataSource
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.data.local.entity.LocalDetailMovie
import com.samsul.moviecatalogue.data.local.entity.LocalDetailTvShow
import com.samsul.moviecatalogue.data.remote.RemoteDataSource
import com.samsul.moviecatalogue.data.remote.detailmodel.DetailMovieResponse
import com.samsul.moviecatalogue.data.remote.detailmodel.DetailTvShowResponse
import com.samsul.moviecatalogue.data.remote.listmodel.DataMovie
import com.samsul.moviecatalogue.data.remote.listmodel.DataTvShow
import com.samsul.moviecatalogue.data.source.DataSource
import com.samsul.moviecatalogue.data.NetworkBoundResource
import com.samsul.moviecatalogue.utils.AppExecutors
import com.samsul.moviecatalogue.vo.Resource

open class DataRepository private constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    val executors: AppExecutors
) : DataSource {

    companion object {
        @Volatile
        private var INSTANCE: DataRepository? = null

        fun getInstance(
            localDataSource: LocalDataSource,
            remoteRepository: RemoteDataSource,
            executors: AppExecutors): DataRepository? =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: DataRepository(localDataSource,remoteRepository,executors).apply {
                    INSTANCE = this
                }
            }

    }

    override fun getDetailMovie(movieId: Int): LiveData<Resource<LocalDetailMovie>> {
        return object : NetworkBoundResource<LocalDetailMovie, DetailMovieResponse>(executors){
            override fun loadFromDB(): LiveData<LocalDetailMovie> {
                return localDataSource.getDetailMovie(movieId)
            }

            override fun shouldFetch(data: LocalDetailMovie?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<DetailMovieResponse>> {
                return remoteDataSource.getDetailMovie(movieId)
            }

            override fun saveCallResult(data: DetailMovieResponse) {
                val detailMovie = LocalDetailMovie(
                    data.id?.toInt(),
                    data.title,
                    data.time,
                    data.star.toString(),
                    data.releaseDate,
                    data.status,
                    data.synopsis,
                    data.imagePath
                )
                localDataSource.insertDetailMovie(detailMovie)
            }

        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: DataLocalMovie, isFavorite: Boolean) =
        executors.diskIO().execute { localDataSource.setFavoriteMovie(movie, isFavorite) }

    override fun getMovieAsPaged(sort: String): LiveData<Resource<PagedList<DataLocalMovie>>> {
        return object : NetworkBoundResource<PagedList<DataLocalMovie>, List<DataMovie>>(executors) {
            override fun loadFromDB(): LiveData<PagedList<DataLocalMovie>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()

                return LivePagedListBuilder(
                    localDataSource.getMovies(sort),
                    config).build()
            }

            override fun shouldFetch(data: PagedList<DataLocalMovie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<DataMovie>>> {
                return remoteDataSource.getListMovie()
            }

            override fun saveCallResult(data: List<DataMovie>) {
                val movies = ArrayList<DataLocalMovie>()
                for(response in data) {
                    val movie = DataLocalMovie(
                        response.id.toInt(),
                        response.titleMovie,
                        response.imagePoster
                    )
                    movies.add(movie)

                }
                localDataSource.insertMovies(movies)
            }

        }.asLiveData()
    }

    override fun getMovieAsPagedBookmark(): LiveData<PagedList<DataLocalMovie>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getMovieBookmark(),config).build()
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<Resource<LocalDetailTvShow>> {
        return object : NetworkBoundResource<LocalDetailTvShow, DetailTvShowResponse>(executors) {
            override fun loadFromDB(): LiveData<LocalDetailTvShow> {
                return localDataSource.getDetailTvShow(tvShowId)
            }

            override fun shouldFetch(data: LocalDetailTvShow?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<DetailTvShowResponse>> {
                return remoteDataSource.getDetailTv(tvShowId)
            }

            override fun saveCallResult(data: DetailTvShowResponse) {
                val detailTvShow = LocalDetailTvShow(
                    data.id?.toInt(),
                    data.title,
                    data.episode,
                    data.star,
                    "${data.firstRelease} - ${data.lastRelease}",
                    data.status,
                    data.synopsis,
                    data.imagePath
                )
                localDataSource.insertDetailTvShow(detailTvShow)
            }

        }.asLiveData()
    }

    override fun setFavoriteTvShow(tvShow: DataLocalTvShow, isFavorite: Boolean) =
        executors.diskIO().execute {
            localDataSource.setFavoriteTvShow(tvShow, isFavorite)
        }

    override fun getTvShowAsPaged(sort: String): LiveData<Resource<PagedList<DataLocalTvShow>>> {

        return object : NetworkBoundResource<PagedList<DataLocalTvShow>, List<DataTvShow>>(executors) {
            override fun loadFromDB(): LiveData<PagedList<DataLocalTvShow>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(
                    localDataSource.getTvShows(sort),
                    config
                ).build()
            }

            override fun shouldFetch(data: PagedList<DataLocalTvShow>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<DataTvShow>>> {
                return remoteDataSource.getListTvShows()
            }

            override fun saveCallResult(data: List<DataTvShow>) {
                val tvShows = ArrayList<DataLocalTvShow>()
                for(response in data) {
                    val tvShow = DataLocalTvShow(
                        response.id.toInt(),
                        response.titleTv,
                        response.imagePoster
                    )
                    tvShows.add(tvShow)
                }
                localDataSource.insertTvShows(tvShows)
            }

        }.asLiveData()
    }

    override fun getTvShowAsPagedBookmark(): LiveData<PagedList<DataLocalTvShow>>{
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()

        return LivePagedListBuilder(localDataSource.getTvShowBookmark(), config).build()
    }


}