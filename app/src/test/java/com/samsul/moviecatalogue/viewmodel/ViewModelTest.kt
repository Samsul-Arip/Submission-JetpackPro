package com.samsul.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.samsul.moviecatalogue.DummyData
import com.samsul.moviecatalogue.data.repository.DataRepository
import com.samsul.moviecatalogue.data.repository.remote.detailmodel.DetailMovieResponse
import com.samsul.moviecatalogue.data.repository.remote.detailmodel.DetailTvShowResponse
import com.samsul.moviecatalogue.data.repository.remote.listmodel.DataMovie
import com.samsul.moviecatalogue.data.repository.remote.listmodel.DataTvShow
import com.samsul.moviecatalogue.ui.detail.DetailViewModel
import com.samsul.moviecatalogue.ui.fragment.movie.MovieViewModel
import com.samsul.moviecatalogue.ui.fragment.tvshow.TvShowViewModel
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*


class ViewModelTest {

    @Rule
    @JvmField
    var instanceTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModelMovie: MovieViewModel? = null
    private var viewModelTvShow: TvShowViewModel? = null
    private var viewModelDetailMov: DetailViewModel? = null
    private var dataRepository = mock(DataRepository::class.java)

    @Before
    fun setUp() {
        viewModelMovie = MovieViewModel(dataRepository)
        viewModelTvShow = TvShowViewModel(dataRepository)
        viewModelDetailMov = DetailViewModel(dataRepository)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<List<DataMovie>>()
        movie.value = DummyData.getDummyRemoteMovie()
        `when`(dataRepository.getListMovie()).thenReturn(movie)
        val observer = mock(Observer::class.java)
        viewModelMovie?.listMovie?.observeForever(observer as Observer<List<DataMovie>>)
        verify(dataRepository).getListMovie()
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<List<DataTvShow>>()
        tvShow.value = DummyData.getDummyRemoteTvShows()
        `when`(dataRepository.getListTvShow()).thenReturn(tvShow)
        val observer = mock(Observer::class.java)
        viewModelTvShow?.listTvShow?.observeForever(observer as Observer<List<DataTvShow>>)
        verify(dataRepository).getListMovie()
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<DataMovie>()
        val movies = MutableLiveData<DetailMovieResponse>()
        movie.value = DummyData.getDummyRemoteMovie()[0]
        movies.value = DummyData.getMovieDetail()
        `when`(dataRepository.getDetailMovie(movie.value!!.id)).thenReturn(movies)
        val observer = mock(Observer::class.java)
        viewModelDetailMov?.getDetailMovie(movie.value!!.id)?.observeForever(observer as Observer<in DetailMovieResponse>)
        verify(dataRepository).getListMovie()

        assertEquals(movie.value!!.id, viewModelDetailMov?.getDetailMovie(movie.value!!.id)?.value?.id)
        assertEquals(movie.value!!.titleMovie, viewModelDetailMov?.getDetailMovie(movie.value!!.id)?.value?.title)
        assertEquals(movie.value!!.imagePoster, viewModelDetailMov?.getDetailMovie(movie.value!!.id)?.value?.imagePath)
    }

    @Test
    fun getDetailTvShow() {
        val tvShow = MutableLiveData<DataTvShow>()
        tvShow.value = DummyData.getDummyRemoteTvShows()[0]
        val tvDetail = MutableLiveData<DetailTvShowResponse>()
        tvDetail.value = DummyData.getTvShowDetail()

        `when`(dataRepository.getDetailTvShow(tvShow.value!!.id)).thenReturn(tvDetail)
        val observer = mock(Observer::class.java)
        viewModelDetailMov?.getDetailTv(tvShow.value!!.id)?.observeForever(observer as Observer<in DetailTvShowResponse>)
        verify(dataRepository).getListTvShow()

        assertEquals(tvShow.value!!.id, viewModelDetailMov?.getDetailTv(tvShow.value!!.id)?.value?.id)
        assertEquals(tvShow.value!!.titleTv, viewModelDetailMov?.getDetailTv(tvShow.value!!.id)?.value?.title)
        assertEquals(tvShow.value!!.imagePoster, viewModelDetailMov?.getDetailTv(tvShow.value!!.id)?.value?.imagePath)

    }

}