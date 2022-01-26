package com.samsul.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.data.local.entity.LocalDetailMovie
import com.samsul.moviecatalogue.data.local.entity.LocalDetailTvShow
import com.samsul.moviecatalogue.vo.Resource

interface DataSource {

    fun getDetailMovie(movieId: Int): LiveData<Resource<LocalDetailMovie>>?
    fun setFavoriteMovie(movie: DataLocalMovie, isFavorite: Boolean)
    fun getMovieAsPaged(sort: String): LiveData<Resource<PagedList<DataLocalMovie>>>
    fun getMovieAsPagedBookmark(): LiveData<PagedList<DataLocalMovie>>


    fun getDetailTvShow(tvShowId: Int): LiveData<Resource<LocalDetailTvShow>>?
    fun setFavoriteTvShow(tvShow: DataLocalTvShow, isFavorite: Boolean)
    fun getTvShowAsPaged(sort: String): LiveData<Resource<PagedList<DataLocalTvShow>>>
    fun getTvShowAsPagedBookmark(): LiveData<PagedList<DataLocalTvShow>>
}