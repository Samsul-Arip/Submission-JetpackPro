package com.samsul.moviecatalogue.data.remote.detailmodel

import com.google.gson.annotations.SerializedName

data class DetailTvShowResponse(
    @SerializedName("id") val id: String?,
    @SerializedName("backdrop_path") val imagePath: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("first_air_date") val firstRelease: String?,
    @SerializedName("last_air_date") val lastRelease: String?,
    @SerializedName("original_name") val title: String?,
    @SerializedName("vote_average") val star: String?,
    @SerializedName("number_of_episodes") val episode: String?,
    @SerializedName("overview") val synopsis: String?
)
