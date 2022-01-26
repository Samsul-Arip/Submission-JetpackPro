package com.samsul.moviecatalogue.utils

import androidx.sqlite.db.SimpleSQLiteQuery

object SortUtils {
    const val NEWEST = "Newest"

    fun getSortedQueryMovie(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM data_movie ")

        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY movieId DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }

    fun getSortedQueryTvShows(filter: String): SimpleSQLiteQuery {
        val simpleQuery = StringBuilder().append("SELECT * FROM data_tvshow ")

        when (filter) {
            NEWEST -> {
                simpleQuery.append("ORDER BY tvShowId DESC")
            }
        }
        return SimpleSQLiteQuery(simpleQuery.toString())
    }
}