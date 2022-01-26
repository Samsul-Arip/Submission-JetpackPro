package com.samsul.moviecatalogue.data.remote

import android.os.Handler
import android.os.Looper
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class RemoteDataSource {

    private val apiKey = BuildConfig.API_KEY
    private var handler = Handler(Looper.getMainLooper())

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        private val TAG = RemoteDataSource::class.java.toString()
        private const val TIME_LIMITS_DELAY: Long = 2000

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource().apply { instance = this }
            }
    }

    fun getListMovie(): LiveData<ApiResponse<List<DataMovie>>> {
        EspressoIdlingResource.increment()
        val movies = MutableLiveData<ApiResponse<List<DataMovie>>>()
        handler.postDelayed({
            ApiConfig.getApiEndPoint().getListMovies(apiKey)
                .enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        if(response.isSuccessful && response.code() == 200) {
                            movies.postValue(ApiResponse.success(response.body()!!.result))
                            EspressoIdlingResource.decrement()
                        } else {
                            EspressoIdlingResource.decrement()
                        }

                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        showLogs(t.printStackTrace().toString())
                        EspressoIdlingResource.decrement()
                    }

                })
        }, TIME_LIMITS_DELAY)

        return movies
    }
    fun getListTvShows(): LiveData<ApiResponse<List<DataTvShow>>> {
        EspressoIdlingResource.increment()
        val tvShows = MutableLiveData<ApiResponse<List<DataTvShow>>>()
        handler.postDelayed({
            ApiConfig.getApiEndPoint().getListTvShows(apiKey)
                .enqueue(object : Callback<TvShowResponse> {
                    override fun onResponse(
                        call: Call<TvShowResponse>,
                        response: Response<TvShowResponse>
                    ) {
                        if(response.isSuccessful && response.code() == 200) {
                            tvShows.postValue(ApiResponse.success(response.body()!!.result))
                            EspressoIdlingResource.decrement()
                        } else {
                            EspressoIdlingResource.decrement()
                        }

                    }

                    override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                        showLogs(t.printStackTrace().toString())
                        EspressoIdlingResource.decrement()
                    }

                })
        }, TIME_LIMITS_DELAY)
        return tvShows
    }

    fun getDetailMovie(id: Int): LiveData<ApiResponse<DetailMovieResponse>> {
        EspressoIdlingResource.increment()
        val resultDetail = MutableLiveData<ApiResponse<DetailMovieResponse>>()
        handler.postDelayed({
            ApiConfig.getApiEndPoint().getDetailMovie(id, apiKey)
                .enqueue(object : Callback<DetailMovieResponse> {
                    override fun onResponse(
                        call: Call<DetailMovieResponse>,
                        response: Response<DetailMovieResponse>
                    ) {
                        if(response.isSuccessful && response.code() == 200) {
                            resultDetail.postValue(ApiResponse.success(response.body()!!))
                            EspressoIdlingResource.decrement()
                        } else {
                            EspressoIdlingResource.decrement()
                        }

                    }

                    override fun onFailure(call: Call<DetailMovieResponse>, t: Throwable) {
                        showLogs(t.printStackTrace().toString())
                        EspressoIdlingResource.decrement()
                    }

                })
        }, TIME_LIMITS_DELAY)

        return resultDetail
    }

    fun getDetailTv(id: Int): LiveData<ApiResponse<DetailTvShowResponse>>{
        EspressoIdlingResource.increment()
        val detailTvShow = MutableLiveData<ApiResponse<DetailTvShowResponse>>()
        handler.postDelayed({
            ApiConfig.getApiEndPoint().getDetailTvShow(id, apiKey)
                .enqueue(object : Callback<DetailTvShowResponse>{
                    override fun onResponse(
                        call: Call<DetailTvShowResponse>,
                        response: Response<DetailTvShowResponse>
                    ) {
                        if(response.isSuccessful && response.code() == 200) {
                            detailTvShow.postValue(ApiResponse.success(response.body()!!))
                            EspressoIdlingResource.decrement()
                        } else {
                            EspressoIdlingResource.decrement()
                        }

                    }

                    override fun onFailure(call: Call<DetailTvShowResponse>, t: Throwable) {
                        showLogs(t.printStackTrace().toString())
                        EspressoIdlingResource.decrement()
                    }

                })
        }, TIME_LIMITS_DELAY)

        return detailTvShow
    }

    fun showLogs(message: String) {
        Log.d(TAG, message)
    }
}