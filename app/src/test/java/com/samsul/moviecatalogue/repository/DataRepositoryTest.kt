package com.samsul.moviecatalogue.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.samsul.moviecatalogue.DummyData
import com.samsul.moviecatalogue.LiveDataTest
import com.samsul.moviecatalogue.PagedListUtils
import com.samsul.moviecatalogue.data.local.LocalDataSource
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.data.local.entity.LocalDetailMovie
import com.samsul.moviecatalogue.data.local.entity.LocalDetailTvShow
import com.samsul.moviecatalogue.data.remote.RemoteDataSource
import com.samsul.moviecatalogue.utils.AppExecutors
import com.samsul.moviecatalogue.utils.SortUtils
import com.samsul.moviecatalogue.vo.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class DataRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val localDataSource = mock(LocalDataSource::class.java)
    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val executors = mock(AppExecutors::class.java)

    private val dataRepository = DummyDataRepository(localDataSource, remoteDataSource, executors)

    private val movies = DummyData.getDummyRemoteMovie()[0]
    private val tvShows = DummyData.getDummyRemoteTvShows()[0]
    private val detailMovie = DummyData.getMovieDetail()
    private val detailTvShow = DummyData.getTvShowDetail()


    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataLocalMovie>
        `when`(localDataSource.getMovies(SortUtils.NEWEST)).thenReturn(dataSourceFactory)
        dataRepository.getMovieAsPaged(SortUtils.NEWEST)

        val moviesPaged = Resource.success(PagedListUtils.mockPagedList(DummyData.getDummyRemoteMovie()))
        verify(localDataSource).getMovies(SortUtils.NEWEST)

        assertNotNull(moviesPaged)
        assertEquals(movies.movieId,detailMovie.movieId)
        assertEquals(movies.titleMovie,detailMovie.titleMovie)
        assertEquals(movies.imagePoster,detailMovie.imagePoster)
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataLocalTvShow>
        `when`(localDataSource.getTvShows(SortUtils.NEWEST)).thenReturn(dataSourceFactory)
        dataRepository.getTvShowAsPaged(SortUtils.NEWEST)

        val tvShowPaged = Resource.success(PagedListUtils.mockPagedList(DummyData.getDummyRemoteTvShows()))
        verify(localDataSource).getTvShows(SortUtils.NEWEST)


        assertNotNull(tvShowPaged)
        assertEquals(tvShows.tvShowId, detailTvShow.tvShowId)
        assertEquals(tvShows.titleTvShow, detailTvShow.titleTvShow)
        assertEquals(tvShows.imageTvShow, detailTvShow.imageTvShow)
    }

    @Test
    fun getBookmarkMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataLocalMovie>
        `when`(localDataSource.getMovieBookmark()).thenReturn(dataSourceFactory)
        dataRepository.getMovieAsPagedBookmark()

        val bookmarkMovie = Resource.success(PagedListUtils.mockPagedList(DummyData.getDummyRemoteMovie()))
        verify(localDataSource).getMovieBookmark()

        assertNotNull(bookmarkMovie)
        assertEquals(movies.movieId,detailMovie.movieId)
        assertEquals(movies.titleMovie,detailMovie.titleMovie)
        assertEquals(movies.imagePoster,detailMovie.imagePoster)
    }

    @Test
    fun getBookmarkTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataLocalTvShow>
        `when`(localDataSource.getTvShowBookmark()).thenReturn(dataSourceFactory)
        dataRepository.getTvShowAsPagedBookmark()

        val bookmarkMovie = Resource.success(PagedListUtils.mockPagedList(DummyData.getDummyRemoteTvShows()))
        verify(localDataSource).getTvShowBookmark()

        assertNotNull(bookmarkMovie)
        assertEquals(movies.movieId,detailMovie.movieId)
        assertEquals(movies.titleMovie,detailMovie.titleMovie)
        assertEquals(movies.imagePoster,detailMovie.imagePoster)
    }

    @Test
    fun getDetailMovie() {
        val dummyMovie = MutableLiveData<LocalDetailMovie>()
        dummyMovie.value = detailMovie
        `when`(localDataSource.getDetailMovie(detailMovie.movieId!!)).thenReturn(dummyMovie)

        val movie = LiveDataTest.getValue(dataRepository.getDetailMovie(detailMovie.movieId!!))

        assertNotNull(movie)
        assertEquals(detailMovie.movieId, movies.movieId)
    }

    @Test
    fun getDetailTvShow() {
        val dummyTvShow = MutableLiveData<LocalDetailTvShow>()
        dummyTvShow.value = detailTvShow
        `when`(localDataSource.getDetailTvShow(detailTvShow.tvShowId!!)).thenReturn(dummyTvShow)

        val tv = LiveDataTest.getValue(dataRepository.getDetailTvShow(detailTvShow.tvShowId!!))

        assertNotNull(tv)
        assertEquals(detailTvShow.tvShowId, detailTvShow.tvShowId)
    }
}