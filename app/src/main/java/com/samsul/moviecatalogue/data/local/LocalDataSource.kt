package com.samsul.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.samsul.moviecatalogue.data.local.database.CatalogueDao
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow

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
     * Below Is For Movie
     */
    fun getMovies(): LiveData<List<DataLocalMovie>> {
        return catalogueDao.getMovies()
    }

    fun getMovieById(movieId: Int): LiveData<DataLocalMovie> {
        return catalogueDao.getMovieById(movieId)
    }

    fun insertMovies(movies: List<DataLocalMovie>) {
        catalogueDao.insertMovies(movies)
    }

    fun updateFavoriteMovie(movie: DataLocalMovie, isFavorite: Boolean) {
        movie.favorite = isFavorite
        catalogueDao.updateMovie(movie)
    }

    fun getMovieAsPaged(): DataSource.Factory<Int, DataLocalMovie> {
        return catalogueDao.getMovieAsPaged()
    }


    /**
     * Below Is For TvShow
     */

    fun getTvShows(): LiveData<List<DataLocalTvShow>> {
        return catalogueDao.getTvShows()
    }
    fun getTvShowById(tvShowId: Int): LiveData<DataLocalTvShow> {
        return catalogueDao.getTvShowById(tvShowId)
    }

    fun insertTvShows(tvShows: List<DataLocalTvShow>) {
        catalogueDao.insertTvShow(tvShows)
    }

    fun updateFavoriteTvshow(tvShow: DataLocalTvShow, isFavorite: Boolean) {
        tvShow.favorite = isFavorite
        catalogueDao.updateTvShow(tvShow)
    }

    fun getTvShowAsPaged(): DataSource.Factory<Int, DataLocalTvShow> {
        return catalogueDao.getTvShowAsPaged()
    }
}