package com.samsul.moviecatalogue.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.samsul.moviecatalogue.BuildConfig
import com.samsul.moviecatalogue.data.ApiResponse
import com.samsul.moviecatalogue.data.remote.detailmodel.DetailMovieResponse
import com.samsul.moviecatalogue.data.remote.detailmodel.DetailTvShowResponse
import com.samsul.moviecatalogue.data.remote.listmodel.DataMovie
import com.samsul.moviecatalogue.data.remote.listmodel.DataTvShow
import com.samsul.moviecatalogue.data.remote.listmodel.MovieResponse
import com.samsul.moviecatalogue.data.remote.listmodel.TvShowResponse
import com.samsul.moviecatalogue.network.ApiConfig
import com.samsul.moviecatalogue.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

open class RemoteDataSource {

    private val apiKey = BuildConfig.API_KEY

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        private val TAG = RemoteDataSource::class.java.toString()

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getListMovie(): LiveData<ApiResponse<List<DataMovie>>> {
        EspressoIdlingResource.increment()
        val movies = MutableLiveData<ApiResponse<List<DataMovie>>>()
        CoroutineScope(Dispatchers.IO).launch {
            ApiConfig.getApiEndPoint().getListMovies(apiKey)
                .enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        try {
                            if (response.isSuccessful && response.code() == 200) {
                                movies.postValue(ApiResponse.success(response.body()!!.result))
                                EspressoIdlingResource.decrement()
                            } else {
                                EspressoIdlingResource.decrement()
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                            EspressoIdlingResource.decrement()
                        }

                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        showLogs(t.printStackTrace().toString())
                        EspressoIdlingResource.decrement()
                    }

                })
        }

        return movies
    }

    fun getListTvShows(): LiveData<ApiResponse<List<DataTvShow>>> {
        EspressoIdlingResource.increment()
        val tvShows = MutableLiveData<ApiResponse<List<DataTvShow>>>()
        CoroutineScope(Dispatchers.IO).launch {
            ApiConfig.getApiEndPoint().getListTvShows(apiKey)
                .enqueue(object : Callback<TvShowResponse> {
                    override fun onResponse(
                        call: Call<TvShowResponse>,
                        response: Response<TvShowResponse>
                    ) {
                        try {
                            if (response.isSuccessful && response.code() == 200) {
                                tvShows.postValue(ApiResponse.success(response.body()!!.result))
                                EspressoIdlingResource.decrement()
                            } else {
                                EspressoIdlingResource.decrement()
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                            EspressoIdlingResource.decrement()
                        }
                    }

                    override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                        showLogs(t.printStackTrace().toString())
                        EspressoIdlingResource.decrement()
                    }

                })
        }

        return tvShows
    }

    fun getDetailMovie(id: Int): LiveData<ApiResponse<DetailMovieResponse>> {
        EspressoIdlingResource.increment()
        val resultDetail = MutableLiveData<ApiResponse<DetailMovieResponse>>()

        CoroutineScope(Dispatchers.IO).launch {
            ApiConfig.getApiEndPoint().getDetailMovie(id, apiKey)
                .enqueue(object : Callback<DetailMovieResponse> {
                    override fun onResponse(
                        call: Call<DetailMovieResponse>,
                        response: Response<DetailMovieResponse>
                    ) {
                        try {
                            if (response.isSuccessful && response.code() == 200) {
                                resultDetail.postValue(ApiResponse.success(response.body()!!))
                                EspressoIdlingResource.decrement()
                            } else {
                                EspressoIdlingResource.decrement()
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                            EspressoIdlingResource.decrement()
                        }

                    }

                    override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                        showLogs(t.printStackTrace().toString())
                        EspressoIdlingResource.decrement()
                    }

                })
        }

        return resultDetail
    }

    fun getDetailTv(id: Int): LiveData<ApiResponse<DetailTvShowResponse>> {
        EspressoIdlingResource.increment()
        val detailTvShow = MutableLiveData<ApiResponse<DetailTvShowResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            ApiConfig.getApiEndPoint().getDetailTvShow(id, apiKey)
                .enqueue(object : Callback<DetailTvShowResponse> {
                    override fun onResponse(
                        call: Call<DetailTvShowResponse>,
                        response: Response<DetailTvShowResponse>
                    ) {
                        try {
                            if (response.isSuccessful && response.code() == 200) {
                                detailTvShow.postValue(ApiResponse.success(response.body()!!))
                                EspressoIdlingResource.decrement()
                            } else {
                                EspressoIdlingResource.decrement()
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                            EspressoIdlingResource.decrement()
                        }

                    }

                    override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                        showLogs(t.printStackTrace().toString())
                        EspressoIdlingResource.decrement()
                    }

                })
        }

        return detailTvShow
    }

    fun showLogs(message: String) {
        Log.d(TAG, message)
    }
}