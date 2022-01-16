package com.samsul.moviecatalogue.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samsul.moviecatalogue.data.repository.DataRepository
import com.samsul.moviecatalogue.inject.Injection
import com.samsul.moviecatalogue.ui.detail.DetailViewModel
import com.samsul.moviecatalogue.ui.fragment.movie.MovieViewModel
import com.samsul.moviecatalogue.ui.fragment.tvshow.TvShowViewModel

class ViewModelFactory(private val dataRepository: DataRepository): ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideDataRepository()).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(dataRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(dataRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
    }

}