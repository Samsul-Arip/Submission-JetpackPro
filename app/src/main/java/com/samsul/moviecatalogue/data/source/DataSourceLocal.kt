package com.samsul.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.vo.Resource

interface DataSourceLocal {

    fun getMovies(): LiveData<Resource<List<DataLocalMovie>>>?
    fun getMovieById(movieId: Int): LiveData<Resource<DataLocalMovie>>?
    fun updateFavoriteMovie(movie: DataLocalMovie, isFavorite: Boolean)
    fun insertMovies(movies: List<DataLocalMovie>)
    fun getMovieAsPaged(): LiveData<Resource<PagedList<DataLocalMovie>>>

    fun getTvShows(): LiveData<Resource<List<DataLocalTvShow>>>?
    fun getTvShowById(tvShowId: Int): LiveData<Resource<DataLocalTvShow>>?
    fun updateFavoriteTvShow(tvShow: DataLocalTvShow, isFavorite: Boolean)
    fun insertTvShow(tvShow: List<DataLocalTvShow>)
    fun getTvShowAsPaged(): LiveData<Resource<PagedList<DataLocalTvShow>>>
}