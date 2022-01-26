package com.samsul.moviecatalogue.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_tvShow")
data class LocalDetailTvShow (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    val tvShowId: Int? = 0,

    @ColumnInfo(name = "titleTvShow")
    val titleTvShow: String? = "",

    @ColumnInfo(name = "time")
    val time: String? = "",

    @ColumnInfo(name = "rating")
    val rating: String? = "",

    @ColumnInfo(name = "release")
    val releaseDate: String? = "",

    @ColumnInfo(name = "status")
    val status: String? = "",

    @ColumnInfo(name = "synopsis")
    val synopsis: String? = "",

    @ColumnInfo(name = "imageTvShow")
    val imageTvShow: String? = "",
)