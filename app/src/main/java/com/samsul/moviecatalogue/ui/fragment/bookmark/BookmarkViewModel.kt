package com.samsul.moviecatalogue.ui.fragment.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.data.repository.DataRepository

class BookmarkViewModel(private val dataRepository: DataRepository): ViewModel() {

    fun getMovieBookmark(): LiveData<PagedList<DataLocalMovie>> = dataRepository.getMovieAsPagedBookmark()

    fun getTvShowBookmark(): LiveData<PagedList<DataLocalTvShow>> = dataRepository.getTvShowAsPagedBookmark()
}