package com.samsul.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.data.local.entity.LocalDetailMovie
import com.samsul.moviecatalogue.data.local.entity.LocalDetailTvShow
import com.samsul.moviecatalogue.data.repository.DataRepository
import com.samsul.moviecatalogue.vo.Resource

class DetailViewModel(private val dataRepository: DataRepository): ViewModel() {

    private var id: Int = 0
    private var idTv: Int = 0

    fun setDetailSelectedMovie(movieId: Int) {
        this.id = movieId
    }

    fun setDetailSelectedTvShow(tvId: Int) {
        this.idTv = tvId
    }

    fun getMovie(): LiveData<Resource<LocalDetailMovie>> = dataRepository.getDetailMovie(id)

    fun getTvShow(): LiveData<Resource<LocalDetailTvShow>> = dataRepository.getDetailTvShow(idTv)

    fun setBookmarkMovie(dataLocalMovie: DataLocalMovie, newState: Boolean) {
        dataRepository.setFavoriteMovie(dataLocalMovie, newState)
    }

    fun setBookmarkTvShow(dataLocalTvShow: DataLocalTvShow, newState: Boolean) {
        dataRepository.setFavoriteTvShow(dataLocalTvShow, newState)
    }


}