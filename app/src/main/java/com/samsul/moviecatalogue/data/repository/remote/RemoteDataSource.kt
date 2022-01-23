package com.samsul.moviecatalogue.data.repository.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.samsul.moviecatalogue.BuildConfig
import com.samsul.moviecatalogue.data.repository.remote.detailmodel.DetailMovieResponse
import com.samsul.moviecatalogue.data.repository.remote.detailmodel.DetailTvShowResponse
import com.samsul.moviecatalogue.data.repository.remote.listmodel.*
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

    fun getListMovie(listMovieCallBack: ListMovieCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            ApiConfig.getApiEndPoint().getListMovies(apiKey)
                .enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        if(response.isSuccessful && response.code() == 200) {
                            listMovieCallBack.onResponse(response.body()!!.result)
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
    }
    fun getListTvShows(listTvShowCallback: ListTvShowCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            ApiConfig.getApiEndPoint().getListTvShows(apiKey)
                .enqueue(object : Callback<TvShowResponse> {
                    override fun onResponse(
                        call: Call<TvShowResponse>,
                        response: Response<TvShowResponse>
                    ) {
                        if(response.isSuccessful && response.code() == 200) {
                            listTvShowCallback.onResponse(response.body()!!.result)
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
    }

    fun getDetailMovie(id: String,detailMovieCallback: DetailMovieCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            ApiConfig.getApiEndPoint().getDetailMovie(id, apiKey)
                .enqueue(object : Callback<DetailMovieResponse> {
                    override fun onResponse(
                        call: Call<DetailMovieResponse>,
                        response: Response<DetailMovieResponse>
                    ) {
                        if(response.isSuccessful && response.code() == 200) {
                            detailMovieCallback.onResponse(response.body()!!)
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
    }

    fun getDetailTv(id: String, detailTvCallback: DetailTvCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            ApiConfig.getApiEndPoint().getDetailTvShow(id, apiKey)
                .enqueue(object : Callback<DetailTvShowResponse>{
                    override fun onResponse(
                        call: Call<DetailTvShowResponse>,
                        response: Response<DetailTvShowResponse>
                    ) {
                        if(response.isSuccessful && response.code() == 200) {
                            detailTvCallback.onResponse(response.body()!!)
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
    }

    fun showLogs(message: String) {
        Log.d(TAG, message)
    }

    interface ListMovieCallback {
        fun onResponse(movieResponse: List<DataMovie>)
    }

    interface ListTvShowCallback {
        fun onResponse(tvResponse: List<DataTvShow>)
    }

    interface DetailMovieCallback {
        fun onResponse(detailMovieResponse: DetailMovieResponse)
    }

    interface DetailTvCallback {
        fun onResponse(detailTvResponse: DetailTvShowResponse)
    }





}