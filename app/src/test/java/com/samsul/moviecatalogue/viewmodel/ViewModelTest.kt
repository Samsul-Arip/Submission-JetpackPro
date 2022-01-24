package com.samsul.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.samsul.moviecatalogue.DummyData
import com.samsul.moviecatalogue.data.repository.RemoteRepository
import com.samsul.moviecatalogue.data.remote.detailmodel.DetailMovieResponse
import com.samsul.moviecatalogue.data.remote.detailmodel.DetailTvShowResponse
import com.samsul.moviecatalogue.data.remote.listmodel.DataMovie
import com.samsul.moviecatalogue.data.remote.listmodel.DataTvShow
import com.samsul.moviecatalogue.ui.detail.DetailViewModel
import com.samsul.moviecatalogue.ui.fragment.movie.MovieViewModel
import com.samsul.moviecatalogue.ui.fragment.tvshow.TvShowViewModel
import junit.framework.Assert.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class ViewModelTest {

    @get:Rule
    var instanceTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModelMovie: MovieViewModel? = null
    private var viewModelTvShow: TvShowViewModel? = null
    private var viewModelDetailMov: DetailViewModel? = null
    private var dataRepository = mock(RemoteRepository::class.java)

    @Mock
    private lateinit var observerTvShow: Observer<List<DataTvShow>>

    @Mock
    private lateinit var observerMovie: Observer<List<DataMovie>>

    @Mock
    private lateinit var observerDetailTv: Observer<DetailTvShowResponse>

    @Mock
    private lateinit var observerDetailMovie: Observer<DetailMovieResponse>

    @Before
    fun setUp() {
        viewModelMovie = MovieViewModel(dataRepository)
        viewModelTvShow = TvShowViewModel(dataRepository)
        viewModelDetailMov = DetailViewModel(dataRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovie = DummyData.getDummyRemoteMovie()
        val movie = MutableLiveData<List<DataMovie>>()
        movie.value = dummyMovie
        lenient().`when`(dataRepository.getListMovie()).thenReturn(movie)
        verify(dataRepository).getListMovie()
        assertNotNull(dummyMovie)
        assertEquals(19, dummyMovie.size)

        viewModelMovie?.listMovie?.observeForever(observerMovie)
        verify(observerMovie, times(0)).onChanged(dummyMovie)

    }

    @Test
    fun getTvShow() {
        val dummyTv = DummyData.getDummyRemoteTvShows()
        val tvShow = MutableLiveData<List<DataTvShow>>()
        tvShow.value = dummyTv
        lenient().`when`(dataRepository.getListTvShow()).thenReturn(tvShow)
        verify(dataRepository).getListTvShow()

        assertNotNull(dummyTv)
        assertEquals(19, dummyTv.size )
        viewModelTvShow?.listTvShow?.observeForever(observerTvShow)
        verify(observerTvShow, times(0)).onChanged(dummyTv)
    }

    @Test
    fun getDetailMovie() {
        val dummyDetailMovie = DummyData.getMovieDetail()
        val movie = MutableLiveData<DataMovie>()
        val movies = MutableLiveData<DetailMovieResponse>()
        movie.value = DummyData.getDummyRemoteMovie()[0]
        movies.value = dummyDetailMovie
        `when`(dataRepository.getDetailMovie(movie.value!!.id)).thenReturn(movies)
        verify(dataRepository).getListMovie()

        assertNotNull(dummyDetailMovie)
        assertEquals(movie.value!!.id, viewModelDetailMov?.getDetailMovie(movie.value!!.id)?.value?.id)
        assertEquals(movie.value!!.titleMovie, viewModelDetailMov?.getDetailMovie(movie.value!!.id)?.value?.title)
        assertEquals(movie.value!!.imagePoster, viewModelDetailMov?.getDetailMovie(movie.value!!.id)?.value?.imagePath)

        viewModelDetailMov?.getDetailMovie(movie.value!!.id)?.observeForever(observerDetailMovie)
        verify(observerDetailMovie).onChanged(dummyDetailMovie)

    }

    @Test
    fun getDetailTvShow() {
        val dummyDetailTv = DummyData.getTvShowDetail()
        val tvShow = MutableLiveData<DataTvShow>()
        tvShow.value = DummyData.getDummyRemoteTvShows()[0]
        val tvDetail = MutableLiveData<DetailTvShowResponse>()
        tvDetail.value = dummyDetailTv

        `when`(dataRepository.getDetailTvShow(tvShow.value!!.id)).thenReturn(tvDetail)
        verify(dataRepository).getListTvShow()

        assertNotNull(dummyDetailTv)
        assertEquals(tvShow.value!!.id, viewModelDetailMov?.getDetailTv(tvShow.value!!.id)?.value?.id)
        assertEquals(tvShow.value!!.titleTv, viewModelDetailMov?.getDetailTv(tvShow.value!!.id)?.value?.title)
        assertEquals(tvShow.value!!.imagePoster, viewModelDetailMov?.getDetailTv(tvShow.value!!.id)?.value?.imagePath)

        viewModelDetailMov?.getDetailTv(tvShow.value!!.id)?.observeForever(observerDetailTv)
        verify(observerDetailTv).onChanged(dummyDetailTv)

    }

}