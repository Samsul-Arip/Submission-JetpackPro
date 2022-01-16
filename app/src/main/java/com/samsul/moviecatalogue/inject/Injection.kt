package com.samsul.moviecatalogue.inject

import com.samsul.moviecatalogue.data.repository.DataRepository
import com.samsul.moviecatalogue.data.repository.remote.RemoteDataSource

object Injection {

    fun provideDataRepository(): DataRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return DataRepository.getInstance(remoteDataSource)
    }
}