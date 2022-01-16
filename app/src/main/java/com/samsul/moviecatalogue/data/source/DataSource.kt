package com.samsul.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.samsul.moviecatalogue.data.repository.remote.detailmodel.DetailMovieResponse
import com.samsul.moviecatalogue.data.repository.remote.detailmodel.DetailTvShowResponse
import com.samsul.moviecatalogue.data.repository.remote.listmodel.DataMovie
import com.samsul.moviecatalogue.data.repository.remote.listmodel.DataTvShow

interface DataSource {
    fun getListMovie(): LiveData<List<DataMovie>>
    fun getListTvShow(): LiveData<List<DataTvShow>>
    fun getDetailMovie(id: String): LiveData<DetailMovieResponse>
    fun getDetailTvShow(id: String): LiveData<DetailTvShowResponse>
}