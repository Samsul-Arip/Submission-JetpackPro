package com.samsul.moviecatalogue.ui.fragment.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.repository.DataRepository
import com.samsul.moviecatalogue.vo.Resource

class MovieViewModel(private val dataRepository: DataRepository): ViewModel() {
    fun listMoviePaged(sort: String): LiveData<Resource<PagedList<DataLocalMovie>>> =
        dataRepository.getMovieAsPaged(sort)
}