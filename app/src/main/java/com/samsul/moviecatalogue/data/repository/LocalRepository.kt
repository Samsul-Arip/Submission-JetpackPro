package com.samsul.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.samsul.moviecatalogue.data.local.database.CatalogueDao
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow

open class LocalRepository constructor(private val catalogueDao: CatalogueDao) {

    companion object {
        @Volatile
        private var INSTANCE: LocalRepository? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: LocalRepository(catalogueDao).apply {
                    INSTANCE = this
                }
            }
    }

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

    fun getMoviesAsPaged(): DataSource.Factory<Int, DataLocalMovie> {
        return catalogueDao.getMovieAsPaged()
    }




    fun getTvShows(): LiveData<List<DataLocalTvShow>> {
        return catalogueDao.getTvShows()
    }

    fun getTvShowById(tvShowId: Int): LiveData<DataLocalTvShow> {
        return catalogueDao.getTvShowById(tvShowId)
    }

    fun insertTvShows(tvShows: List<DataLocalTvShow>) {
        catalogueDao.insertTvShow(tvShows)
    }

    fun updateFavoriteTvShow(tvShow: DataLocalTvShow, isFavorite: Boolean) {
        tvShow.favorite = isFavorite
        catalogueDao.updateTvShow(tvShow)
    }

    fun getTvShowAsPaged(): DataSource.Factory<Int, DataLocalTvShow> {
        return catalogueDao.getTvShowAsPaged()
    }

}