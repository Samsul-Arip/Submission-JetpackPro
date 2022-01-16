package com.samsul.moviecatalogue.data.repository.remote.listmodel

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val result: List<DataMovie>
)