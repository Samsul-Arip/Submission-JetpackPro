package com.samsul.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.data.repository.DataRepository
import com.samsul.moviecatalogue.vo.Resource

class DetailViewModel(private val dataRepository: DataRepository): ViewModel() {

//    fun movieDetail(id: String): LiveData<DataLocalMovie> = dataRepository.getDetailMovie(id)
//
//    fun tvShowDetail(id: String): LiveData<DataLocalTvShow> = dataRepository.getDetailTvShow(id)
//
//    val movieId = MutableLiveData<Int>()
//    val tvShowId = MutableLiveData<Int>()
//
//    fun setMovieId(movieId: Int) {
//        this.movieId.value = movieId
//    }
//
//    fun setTvShowId(tvShowId: Int) {
//        this.tvShowId.value = tvShowId
//    }
//
//    private val getTvShow: LiveData<Resource<DataLocalTvShow>> = Transformations.switchMap(tvShowId) {
//        dataRepository.getTvShowById(it)
//    }
//    private val getMovie: LiveData<Resource<DataLocalMovie>> = Transformations.switchMap(movieId) {
//        dataRepository.getMovieById(it)
//    }
//
//    fun setFavoriteMovie(){
//        getMovie.value?.data?.let {
//            val newState = it.favorite
//            dataRepository.updateFavoriteMovie(it, newState)
//        }
//    }
//
//    fun setFavoriteTvShow() {
//        getTvShow.value?.data?.let {
//            val newState = it.favorite
//            dataRepository.updateFavoriteTvShow(it, newState)
//        }
//    }

}