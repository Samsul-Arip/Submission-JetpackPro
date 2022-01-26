package com.samsul.moviecatalogue.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "data_tvshow")
data class DataLocalTvShow(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    val tvShowId: Int? = 0,

    @ColumnInfo(name = "titleTvShow")
    val titleTvShow: String? = "",

    @ColumnInfo(name = "imageTvShow")
    val imageTvShow: String? = "",

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false

): Parcelable
