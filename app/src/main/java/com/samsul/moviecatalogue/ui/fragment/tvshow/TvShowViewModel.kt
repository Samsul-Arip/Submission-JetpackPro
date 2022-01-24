package com.samsul.moviecatalogue.ui.fragment.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.data.repository.DataRepository
import com.samsul.moviecatalogue.vo.Resource

class TvShowViewModel(private val dataRepository: DataRepository): ViewModel(){

    fun listTvShow(): LiveData<Resource<List<DataLocalTvShow>>> = dataRepository.getTvShows()


}