package com.samsul.moviecatalogue.data.local.database

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.data.local.entity.LocalDetailMovie
import com.samsul.moviecatalogue.data.local.entity.LocalDetailTvShow

@Dao
interface CatalogueDao {
    /**
     * Below Is For Movie
     */
//    @Query("SELECT * FROM data_movie")
    @RawQuery(observedEntities = [DataLocalMovie::class])
    fun getMovies(query: SupportSQLiteQuery): DataSource.Factory<Int, DataLocalMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(dataMovie: List<DataLocalMovie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailMovie(movie: LocalDetailMovie)

    @Query("SELECT * FROM detail_movie WHERE movieId = :movieId")
    fun getDetailMovie(movieId: Int): LiveData<LocalDetailMovie>

    @Update
    fun updateMovie(movie: DataLocalMovie)


    @Query("SELECT * FROM data_movie WHERE favorite = 1")
    fun getMovieBookmark(): DataSource.Factory<Int, DataLocalMovie>


    /**
     * Below Is For TvShow
     */
//    @Query("SELECT * FROM data_tvshow")
    @RawQuery(observedEntities = [DataLocalTvShow::class])
    fun getTvShows(query: SupportSQLiteQuery): DataSource.Factory<Int, DataLocalTvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<DataLocalTvShow>)

    @Update
    fun updateTvShow(tvShow: DataLocalTvShow)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailTvShow(tvShow: LocalDetailTvShow)

    @Query("SELECT * FROM detail_tvshow WHERE tvShowId = :tvShowId")
    fun getDetailTvShow(tvShowId: Int): LiveData<LocalDetailTvShow>

    @Query("SELECT * FROM data_tvshow WHERE favorite = 1")
    fun getTvShowsBookmark(): DataSource.Factory<Int, DataLocalTvShow>
}