package com.samsul.moviecatalogue.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.eq
import com.samsul.moviecatalogue.DummyData
import com.samsul.moviecatalogue.LiveDataTest
import com.samsul.moviecatalogue.data.repository.remote.RemoteDataSource
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class DataRepositoryTest {

    @Rule
    @JvmField
    var instantTask = InstantTaskExecutorRule()
    private val remoteDataSource = mock(RemoteDataSource::class.java)
    private val dataRepositoryTest = DummyDataRepository(remoteDataSource)

    private val listMovie = DummyData.getDummyRemoteMovie()
    private val movieId = DummyData.getDummyRemoteMovie()[0].id

    private val listTvShow = DummyData.getDummyRemoteTvShows()
    private val tvId = DummyData.getDummyRemoteTvShows()[0].id

    private val detailMovie = DummyData.getMovieDetail()
    private val detailTvShow = DummyData.getTvShowDetail()


    private fun <T> anyOfT(type: Class<T>): T = any(type)


    private fun <T> eqOfT(obj: T): T = eq(obj)

    @Test
    fun getListMovie() {
        doAnswer {
            val callback = it.arguments[0] as RemoteDataSource.ListMovieCallback
            callback.onResponse(listMovie)
            null
        }.`when`(remoteDataSource).getListMovie(anyOfT(RemoteDataSource.ListMovieCallback::class.java))
        val result = LiveDataTest.getValue(dataRepositoryTest.getListMovie())
        assertEquals(listMovie.size, result.size)
    }

    @Test
    fun getListTvShow() {
        doAnswer {
            val callback = it.arguments[0] as RemoteDataSource.ListTvShowCallback
            callback.onResponse(listTvShow)
            null
        }.`when`(remoteDataSource).getListTvShows(anyOfT(RemoteDataSource.ListTvShowCallback::class.java))

        val result = LiveDataTest.getValue(dataRepositoryTest.getListTvShow())
        assertEquals(listTvShow.size, result.size)
    }

    @Test
    fun getMovieDetail() {
        doAnswer {
            val callback = it.arguments[0] as RemoteDataSource.DetailMovieCallback
            callback.onResponse(detailMovie)
            null
        }.`when`(remoteDataSource).getDetailMovie(
            eqOfT(movieId),
            anyOfT(RemoteDataSource.DetailMovieCallback::class.java))
    }

    @Test
    fun getTvShowDetail() {
        doAnswer {
            val callback = it.arguments[0] as RemoteDataSource.DetailTvCallback
            callback.onResponse(detailTvShow)
            null
        }.`when`(remoteDataSource).getDetailTv(
            eqOfT(tvId),
            anyOfT(RemoteDataSource.DetailTvCallback::class.java)
        )
    }
}