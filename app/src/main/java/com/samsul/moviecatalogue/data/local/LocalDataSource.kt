package com.samsul.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.samsul.moviecatalogue.data.local.database.CatalogueDao
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.data.local.entity.LocalDetailMovie
import com.samsul.moviecatalogue.data.local.entity.LocalDetailTvShow
import com.samsul.moviecatalogue.utils.SortUtils

open class LocalDataSource constructor(private val catalogueDao: CatalogueDao) {

    companion object {
        @Volatile
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocalDataSource(catalogueDao).apply {
                    INSTANCE = this
                }
            }
    }

    /**
     * Below For Movie
     */

    fun insertMovies(movies: List<DataLocalMovie>) {
        catalogueDao.insertMovies(movies)
    }

    fun getMovies(sort: String): DataSource.Factory<Int, DataLocalMovie> {
        val query = SortUtils.getSortedQueryMovie(sort)
        return catalogueDao.getMovies(query)
    }

    fun insertDetailMovie(movie: LocalDetailMovie) {
        catalogueDao.insertDetailMovie(movie)
    }

    fun getDetailMovie(movieId: Int): LiveData<LocalDetailMovie> {
        return catalogueDao.getDetailMovie(movieId)
    }

    fun getMovieBookmark(): DataSource.Factory<Int, DataLocalMovie> {
        return catalogueDao.getMovieBookmark()
    }

    fun setFavoriteMovie(movie: DataLocalMovie, isFavorite: Boolean) {
        movie.favorite = isFavorite
        catalogueDao.updateMovie(movie)
    }


    /**
     * Below For TvShow
     */

    fun insertTvShows(tvShows: List<DataLocalTvShow>) {
        catalogueDao.insertTvShow(tvShows)
    }

    fun getTvShows(sort: String): DataSource.Factory<Int, DataLocalTvShow> {
        val query = SortUtils.getSortedQueryTvShows(sort)
        return catalogueDao.getTvShows(query)
    }

    fun insertDetailTvShow(tvShow: LocalDetailTvShow) {
        catalogueDao.insertDetailTvShow(tvShow)
    }

    fun getDetailTvShow(tvShowId: Int): LiveData<LocalDetailTvShow> {
        return catalogueDao.getDetailTvShow(tvShowId)
    }

    fun getTvShowBookmark(): DataSource.Factory<Int, DataLocalTvShow> {
        return catalogueDao.getTvShowsBookmark()
    }

    fun setFavoriteTvShow(tvShow: DataLocalTvShow, isFavorite: Boolean) {
        tvShow.favorite = isFavorite
        catalogueDao.updateTvShow(tvShow)
    }



}