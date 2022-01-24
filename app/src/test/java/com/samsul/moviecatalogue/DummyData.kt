package com.samsul.moviecatalogue

import com.samsul.moviecatalogue.data.remote.detailmodel.DataGenre
import com.samsul.moviecatalogue.data.remote.detailmodel.DetailMovieResponse
import com.samsul.moviecatalogue.data.remote.detailmodel.DetailTvShowResponse
import com.samsul.moviecatalogue.data.remote.listmodel.DataMovie
import com.samsul.moviecatalogue.data.remote.listmodel.DataTvShow


object DummyData {

    fun getDummyRemoteMovie(): List<DataMovie> =
        arrayListOf(
            DataMovie("438695", "Sing 2", "/aWeKITRFbbwY8txG5uCj4rMCfSP.jpg"),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
            DataMovie("0", "", ""),
        )

    fun getDummyRemoteTvShows(): List<DataTvShow> =
        arrayListOf(
            DataTvShow(
                "85552",
                "Euphoria",
                "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg"
            ),
            DataTvShow("1", "", ""),
            DataTvShow("2", "", ""),
            DataTvShow("2", "", ""),
            DataTvShow("2", "", ""),
            DataTvShow("2", "", ""),
            DataTvShow("2", "", ""),
            DataTvShow("2", "", ""),
            DataTvShow("2", "", ""),
            DataTvShow("2", "", ""),
            DataTvShow("2", "", ""),
            DataTvShow("2", "", ""),
            DataTvShow("2", "", ""),
            DataTvShow("2", "", ""),
            DataTvShow("2", "", ""),
            DataTvShow("2", "", ""),
            DataTvShow("2", "", ""),
            DataTvShow("2", "", ""),
            DataTvShow("2", "", ""),
        )

    fun getMovieDetail(): DetailMovieResponse =
        DetailMovieResponse(
            "438695",
            "/aWeKITRFbbwY8txG5uCj4rMCfSP.jpg",
            8.4,
            "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
            "Sing 2",
            "2021-12-01",
            listOf(
                DataGenre("Animation"),
                DataGenre( "Comedy"),
                DataGenre("Family"),
                DataGenre("Music")
            ),
            "110"
        )

    fun getTvShowDetail(): DetailTvShowResponse =
        DetailTvShowResponse(
            "85552",
            "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
            listOf(DataGenre("Drama")),
            "2019-06-16",
            "2022-01-09",
            "Euphoria",
            "8.4",
            "11",
            "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media."
        )
}