package com.samsul.moviecatalogue.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_movie")
data class DataLocalMovie(
    @PrimaryKey
    @ColumnInfo(name = "movieId")
    val movieId: Int? = 0,

    @ColumnInfo(name = "titleMovie")
    val titleMovie: String? = "",

    @ColumnInfo(name = "time")
    val time: String? = "",

    @ColumnInfo(name = "rating")
    val rating: String? = "",

    @ColumnInfo(name = "release")
    val releaseDate: String? = "",

    @ColumnInfo(name = "genre")
    val genre: String? = "",

    @ColumnInfo(name = "synopsis")
    val synopsis: String? = "",

    @ColumnInfo(name = "imageMovie")
    val imagePoster: String? = "",

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false

)
