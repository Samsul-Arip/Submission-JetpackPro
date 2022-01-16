package com.samsul.moviecatalogue.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.samsul.moviecatalogue.data.repository.remote.RemoteDataSource
import com.samsul.moviecatalogue.data.repository.remote.detailmodel.DetailMovieResponse
import com.samsul.moviecatalogue.data.repository.remote.detailmodel.DetailTvShowResponse
import com.samsul.moviecatalogue.data.repository.remote.listmodel.DataMovie
import com.samsul.moviecatalogue.data.repository.remote.listmodel.DataTvShow
import com.samsul.moviecatalogue.data.source.DataSource

class DataRepository(private val remoteDataSource: RemoteDataSource): DataSource {

    companion object {
        @Volatile
        private var instance: DataRepository? = null

        fun getInstance(remoteDataSource: RemoteDataSource): DataRepository =
            instance ?: synchronized(this) {
                instance ?: DataRepository(remoteDataSource).apply { instance = null }
            }
    }

    override fun getListMovie(): LiveData<List<DataMovie>> {
        val listMovie = MutableLiveData<List<DataMovie>>()
        remoteDataSource.getListMovie(object : RemoteDataSource.ListMovieCallback {
            override fun onResponse(movieResponse: List<DataMovie>) {
                listMovie.postValue(movieResponse)
            }

        })
        return listMovie
    }

    override fun getListTvShow(): LiveData<List<DataTvShow>> {
        val listTv = MutableLiveData<List<DataTvShow>>()
        remoteDataSource.getListTvShows(object : RemoteDataSource.ListTvShowCallback {
            override fun onResponse(tvResponse: List<DataTvShow>) {
                listTv.postValue(tvResponse)
            }
        })
        return listTv
    }

    override fun getDetailMovie(id: String): LiveData<DetailMovieResponse> {
        val detailMovie = MutableLiveData<DetailMovieResponse>()
        remoteDataSource.getDetailMovie(id, object : RemoteDataSource.DetailMovieCallback {
            override fun onResponse(detailMovieResponse: DetailMovieResponse) {
                detailMovie.postValue(detailMovieResponse)
            }
        })
        return detailMovie
    }

    override fun getDetailTvShow(id: String): LiveData<DetailTvShowResponse> {
        val detailTv = MutableLiveData<DetailTvShowResponse>()

        remoteDataSource.getDetailTv(id, object : RemoteDataSource.DetailTvCallback{
            override fun onResponse(detailTvResponse: DetailTvShowResponse) {
                detailTv.postValue(detailTvResponse)
            }
        })
        return detailTv
    }


}