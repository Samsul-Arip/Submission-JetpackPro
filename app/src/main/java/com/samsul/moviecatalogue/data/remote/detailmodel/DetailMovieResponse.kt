package com.samsul.moviecatalogue.data.remote.detailmodel

import com.google.gson.annotations.SerializedName

data class DetailMovieResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("backdrop_path") val imagePath: String?,
    @SerializedName("vote_average") val star: Double?,
    @SerializedName("overview") val synopsis: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("genres") val genre: List<DataGenre>?,
    @SerializedName("runtime") val time: String?,
)
