package com.samsul.moviecatalogue.data.repository.remote.listmodel

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataTvShow(
    @SerializedName("id") val id: String,
    @SerializedName("name") val titleTv: String,
    @SerializedName("poster_path") val imagePoster : String
): Parcelable
