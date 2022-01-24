package com.samsul.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.samsul.moviecatalogue.data.ApiResponse
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.data.remote.RemoteDataSource
import com.samsul.moviecatalogue.data.remote.detailmodel.DetailMovieResponse
import com.samsul.moviecatalogue.data.remote.detailmodel.DetailTvShowResponse
import com.samsul.moviecatalogue.data.remote.listmodel.DataMovie
import com.samsul.moviecatalogue.data.remote.listmodel.DataTvShow
import com.samsul.moviecatalogue.data.source.DataSource
import com.samsul.moviecatalogue.data.source.DataSourceLocal
import com.samsul.moviecatalogue.data.source.NetworkBoundResource
import com.samsul.moviecatalogue.utils.AppExecutors
import com.samsul.moviecatalogue.vo.Resource

open class DataRepository private constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteDataSource,
    val executors: AppExecutors
) : DataSourceLocal {

    companion object {
        @Volatile
        private var INSTANCE: DataRepository? = null

        fun getInstance(
            localRepository: LocalRepository,
            remoteRepository: RemoteDataSource,
            executors: AppExecutors): DataRepository? =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: DataRepository(localRepository,remoteRepository,executors).apply {
                    INSTANCE = this
                }
            }

    }

    override fun getMovies(): LiveData<Resource<List<DataLocalMovie>>> {

        return object : NetworkBoundResource<List<DataLocalMovie>, List<DataMovie>>(executors) {
            override fun loadFromDB(): LiveData<List<DataLocalMovie>> {
                return localRepository.getMovies()
            }

            override fun shouldFetch(data: List<DataLocalMovie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<DataMovie>>>{
                return remoteRepository.getListMovie()
            }

            override fun saveCallResult(data: List<DataMovie>) {
                val movies = ArrayList<DataLocalMovie>()
                for(response in data) {
                    val movie = DataLocalMovie(
                        response.id.toInt(),
                        response.titleMovie,
                        "",
                        "","","","",
                        response.imagePoster
                    )
                    movies.add(movie)
                }
                localRepository.insertMovies(movies)
            }

        }.asLiveData()
    }

    override fun getMovieById(movieId: Int): LiveData<Resource<DataLocalMovie>>? {
        return object : NetworkBoundResource<DataLocalMovie, DataMovie>(executors) {

            override fun loadFromDB(): LiveData<DataLocalMovie> {
                return localRepository.getMovieById(movieId)
            }

            override fun shouldFetch(data: DataLocalMovie?): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<DataMovie>>? {
                return null
            }

            override fun saveCallResult(data: DataMovie) {

            }

        }.asLiveData()
    }

    override fun updateFavoriteMovie(movie: DataLocalMovie, isFavorite: Boolean) {
        val runnable = {
            localRepository.updateFavoriteMovie(movie, isFavorite)
        }
        executors.diskIO().execute(runnable)
    }

    override fun insertMovies(movies: List<DataLocalMovie>) {
        val runnable = {
            if(localRepository.getMovies().value.isNullOrEmpty()) {
                localRepository.insertMovies(movies)
            }
        }
        executors.diskIO().execute(runnable)
    }

    override fun getMovieAsPaged(): LiveData<Resource<PagedList<DataLocalMovie>>> {
        return object : NetworkBoundResource<PagedList<DataLocalMovie>, List<DataMovie>>(executors) {
            override fun loadFromDB(): LiveData<PagedList<DataLocalMovie>> {
                return LivePagedListBuilder(
                    localRepository.getMoviesAsPaged(),
                    10
                ).build()
            }

            override fun shouldFetch(data: PagedList<DataLocalMovie>?): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<List<DataMovie>>>? {
                return null
            }

            override fun saveCallResult(data: List<DataMovie>) {

            }

        }.asLiveData()
    }

    override fun getTvShows(): LiveData<Resource<List<DataLocalTvShow>>> {
        return object : NetworkBoundResource<List<DataLocalTvShow>, List<DataTvShow>>(executors) {
            override fun loadFromDB(): LiveData<List<DataLocalTvShow>> {
                return localRepository.getTvShows()
            }

            override fun shouldFetch(data: List<DataLocalTvShow>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<DataTvShow>>> {
                return remoteRepository.getListTvShows()
            }

            override fun saveCallResult(data: List<DataTvShow>) {
                val tvShows = ArrayList<DataLocalTvShow>()
                for(response in data) {
                    val tvShow = DataLocalTvShow(
                        response.id.toInt(),
                        response.titleTv,
                        "",
                        "","","","",
                        response.imagePoster
                    )
                    tvShows.add(tvShow)
                }
                localRepository.insertTvShows(tvShows)
            }

        }.asLiveData()
    }

    override fun getTvShowById(tvShowId: Int): LiveData<Resource<DataLocalTvShow>>? {
        return object : NetworkBoundResource<DataLocalTvShow, DataTvShow>(executors) {
            override fun loadFromDB(): LiveData<DataLocalTvShow> {
                return localRepository.getTvShowById(tvShowId)
            }

            override fun shouldFetch(data: DataLocalTvShow?): Boolean {
                return false
            }

            override fun createCall(): LiveData<ApiResponse<DataTvShow>>? {
                return null
            }

            override fun saveCallResult(data: DataTvShow) {

            }

        }.asLiveData()
    }

    override fun updateFavoriteTvShow(tvShow: DataLocalTvShow, isFavorite: Boolean) {
        val runnable = {
            localRepository.updateFavoriteTvShow(tvShow, isFavorite)
        }
        executors.diskIO().execute(runnable)
    }

    override fun insertTvShow(tvShow: List<DataLocalTvShow>) {
        val runnable = {
            if(localRepository.getTvShows().value.isNullOrEmpty()) {
                localRepository.insertTvShows(tvShow)
            }
        }
        executors.diskIO().execute(runnable)
    }

    override fun getTvShowAsPaged(): LiveData<Resource<PagedList<DataLocalTvShow>>> {
        return object : NetworkBoundResource<PagedList<DataLocalTvShow>, List<DataTvShow>>(executors) {
            override fun loadFromDB(): LiveData<PagedList<DataLocalTvShow>> {
                return LivePagedListBuilder(
                    localRepository.getTvShowAsPaged(),
                    10
                ).build()
            }

            override fun shouldFetch(data: PagedList<DataLocalTvShow>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<DataTvShow>>>? {
                return null
            }

            override fun saveCallResult(data: List<DataTvShow>) {

            }

        }.asLiveData()
    }



}