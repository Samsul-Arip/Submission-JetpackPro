package com.samsul.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.samsul.moviecatalogue.data.ApiResponse
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.data.remote.detailmodel.DetailMovieResponse
import com.samsul.moviecatalogue.data.remote.detailmodel.DetailTvShowResponse
import com.samsul.moviecatalogue.data.remote.listmodel.DataMovie
import com.samsul.moviecatalogue.data.remote.listmodel.DataTvShow
import com.samsul.moviecatalogue.vo.Resource

interface DataSource {
    fun getListMovie(): LiveData<Resource<List<DataLocalMovie>>>
    fun getListTvShow(): LiveData<Resource<List<DataLocalTvShow>>>
    fun getDetailMovie(id: String): LiveData<Resource<DataLocalMovie>>
    fun getDetailTvShow(id: String): LiveData<Resource<DataLocalTvShow>>
}