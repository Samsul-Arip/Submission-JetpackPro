package com.samsul.moviecatalogue.data.repository.remote.listmodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataMovie(
    @SerializedName("id") val id: String,
    @SerializedName("title") val titleMovie: String,
    @SerializedName("poster_path") val imagePoster: String
): Parcelable
