package com.samsul.moviecatalogue.network

import com.samsul.moviecatalogue.data.remote.detailmodel.DetailMovieResponse
import com.samsul.moviecatalogue.data.remote.detailmodel.DetailTvShowResponse
import com.samsul.moviecatalogue.data.remote.listmodel.MovieResponse
import com.samsul.moviecatalogue.data.remote.listmodel.TvShowResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndPoint {
    /**
     * List Movie and tvShow
     */

    @GET("discover/movie")
    fun getListMovies(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("discover/tv")
    fun getListTvShows(
        @Query("api_key") apiKey: String
    ): Call<TvShowResponse>

    /**
     * Detail Movie and TvShow
     */

    @GET("movie/{id}")
    fun getDetailMovie(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<DetailMovieResponse>

    @GET("tv/{id}")
    fun getDetailTvShow(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ): Call<DetailTvShowResponse>
}