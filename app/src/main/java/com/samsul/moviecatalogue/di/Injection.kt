package com.samsul.moviecatalogue.di

import android.app.Application
import android.content.Context
import android.widget.RemoteViews
import com.samsul.moviecatalogue.data.local.LocalDataSource
import com.samsul.moviecatalogue.data.local.database.CatalogueDatabase
import com.samsul.moviecatalogue.data.remote.RemoteDataSource
import com.samsul.moviecatalogue.data.repository.DataRepository
import com.samsul.moviecatalogue.data.repository.LocalRepository
import com.samsul.moviecatalogue.utils.AppExecutors


object Injection {


    fun provideDataRepository(context: Context): DataRepository? {
        val localRepository = LocalRepository.getInstance(CatalogueDatabase.getInstance(context).catalogueDao())
        val remoteRepository = RemoteDataSource.getInstance()

        val executors = AppExecutors()
        return DataRepository.getInstance(localRepository, remoteRepository, executors)

    }

}