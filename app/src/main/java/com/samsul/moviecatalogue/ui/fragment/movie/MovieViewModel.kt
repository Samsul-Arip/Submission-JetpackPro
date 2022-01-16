package com.samsul.moviecatalogue.ui.fragment.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.samsul.moviecatalogue.data.repository.DataRepository
import com.samsul.moviecatalogue.data.repository.remote.listmodel.DataMovie

class MovieViewModel(dataRepository: DataRepository): ViewModel() {
    val listMovie: LiveData<List<DataMovie>> = dataRepository.getListMovie()
}