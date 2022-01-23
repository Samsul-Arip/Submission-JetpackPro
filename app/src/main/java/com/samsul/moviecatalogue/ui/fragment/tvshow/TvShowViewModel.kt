package com.samsul.moviecatalogue.ui.fragment.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.samsul.moviecatalogue.data.repository.DataRepository
import com.samsul.moviecatalogue.data.repository.remote.listmodel.DataTvShow

class TvShowViewModel(dataRepository: DataRepository): ViewModel(){
    val listTvShow: LiveData<List<DataTvShow>> = dataRepository.getListTvShow()

}