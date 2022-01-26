package com.samsul.moviecatalogue

import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.data.local.entity.LocalDetailMovie
import com.samsul.moviecatalogue.data.local.entity.LocalDetailTvShow
import com.samsul.moviecatalogue.data.remote.detailmodel.DetailMovieResponse
import com.samsul.moviecatalogue.data.remote.detailmodel.DetailTvShowResponse
import com.samsul.moviecatalogue.data.remote.listmodel.DataMovie
import com.samsul.moviecatalogue.data.remote.listmodel.DataTvShow


object DummyData {

    fun getDummyRemoteMovie(): List<DataLocalMovie> =
        arrayListOf(
            DataLocalMovie(
                movieId = 916740,
                titleMovie = "Brazen",
                imagePoster = "/7e4n1GfC9iky9VQzH3cDQz9wYpO.jpg",
            ),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"",""),
            DataLocalMovie(0,"","")

        )

    fun getDummyRemoteTvShows(): List<DataLocalTvShow> =
        arrayListOf(
            DataLocalTvShow(
                tvShowId = 153748,
                titleTvShow = "Big Brother Famosos",
                imageTvShow = "/ynFd1Xmr2r05qPqalNZnh2uxuJ0.jpg"
            ),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
            DataLocalTvShow(1,"",""),
        )

    fun getMovieDetail(): LocalDetailMovie =
        LocalDetailMovie(
            movieId = 916740,
            imagePoster = "/7e4n1GfC9iky9VQzH3cDQz9wYpO.jpg",
            genre = "Released",
            rating = "4.8",
            synopsis = "Mystery writer Grace Miller has killer instincts when it comes to motive - and she'll need every bit of expertise to help solve her sister's murder.",
            titleMovie = "Brazen",
            releaseDate = "2022-01-13",
            time = "94"
        )

    fun getTvShowDetail(): LocalDetailTvShow =
        LocalDetailTvShow(
            tvShowId = 153748,
            imageTvShow = "/ynFd1Xmr2r05qPqalNZnh2uxuJ0.jpg",
            status = "Returning Series",
            releaseDate = "2002-09-08",
            titleTvShow = "Big Brother Famosos",
            rating = "0.0",
            time = "135",
            synopsis = "Big Brother Famosos is the celebrity version of Big Brother Portugal."
        )
}