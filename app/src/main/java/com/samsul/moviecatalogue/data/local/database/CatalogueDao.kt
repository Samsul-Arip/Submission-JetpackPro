package com.samsul.moviecatalogue.data.local.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow

@Dao
interface CatalogueDao {
    /**
     * Below Is For Movie
     */
    @Query("SELECT * FROM data_movie")
    fun getMovies(): LiveData<List<DataLocalMovie>>

    @Transaction
    @Query("SELECT * FROM data_movie WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): LiveData<DataLocalMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(dataMovie: List<DataLocalMovie>)

    @Update
    fun updateMovie(movie: DataLocalMovie): Int

    @WorkerThread
    @Query("SELECT * FROM data_movie WHERE favorite = 1")
    fun getMovieAsPaged(): DataSource.Factory<Int, DataLocalMovie>


    /**
     * Below Is For TvShow
     */
    @Query("SELECT * FROM data_tvshow")
    fun getTvShows(): LiveData<List<DataLocalTvShow>>

    @Transaction
    @Query("SELECT * FROM data_tvshow WHERE tvShowId = :tvShowId")
    fun getTvShowById(tvShowId: Int): LiveData<DataLocalTvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<DataLocalTvShow>)

    @Update(onConflict = OnConflictStrategy.FAIL)
    fun updateTvShow(tvShow: DataLocalTvShow): Int

    @WorkerThread
    @Query("SELECT * FROM data_tvshow WHERE favorite = 1")
    fun getTvShowsBookmark(): LiveData<List<DataLocalTvShow>>

    @Query("SELECT * FROM data_tvshow WHERE favorite = 1")
    fun getTvShowAsPaged(): DataSource.Factory<Int, DataLocalTvShow>
}