package com.samsul.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.samsul.moviecatalogue.data.repository.DataRepository
import com.samsul.moviecatalogue.data.repository.remote.detailmodel.DetailMovieResponse
import com.samsul.moviecatalogue.data.repository.remote.detailmodel.DetailTvShowResponse

class DetailViewModel(private val dataRepository: DataRepository): ViewModel() {

    fun getDetailMovie(id: String): LiveData<DetailMovieResponse> {
        return dataRepository.getDetailMovie(id)
    }

    fun getDetailTv(id: String): LiveData<DetailTvShowResponse> {
        return dataRepository.getDetailTvShow(id)
    }
}