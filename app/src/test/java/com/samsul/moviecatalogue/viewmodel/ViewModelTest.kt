package com.samsul.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.samsul.moviecatalogue.DummyData
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.data.local.entity.LocalDetailMovie
import com.samsul.moviecatalogue.data.local.entity.LocalDetailTvShow
import com.samsul.moviecatalogue.data.repository.DataRepository
import com.samsul.moviecatalogue.ui.detail.DetailViewModel
import com.samsul.moviecatalogue.ui.fragment.movie.MovieViewModel
import com.samsul.moviecatalogue.ui.fragment.tvshow.TvShowViewModel
import com.samsul.moviecatalogue.utils.SortUtils
import com.samsul.moviecatalogue.vo.Resource
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

    private val listMovie = DummyData.getDummyRemoteMovie()[0]
    private val listTvShows = DummyData.getDummyRemoteTvShows()[0]
    private val movieId = listMovie.movieId
    private val tvShowId = listTvShows.tvShowId



    private var dataRepository = mock(DataRepository::class.java)

    @Mock
    private lateinit var observerTvShows: Observer<Resource<PagedList<DataLocalTvShow>>>

    @Mock
    private lateinit var observerMovies: Observer<Resource<PagedList<DataLocalMovie>>>

    @Mock
    private lateinit var observerDetailTv: Observer<Resource<LocalDetailTvShow>>

    @Mock
    private lateinit var observerDetailMovie: Observer<Resource<LocalDetailMovie>>

    @Mock
    private lateinit var pagedListMovie: PagedList<DataLocalMovie>

    @Mock
    private lateinit var pagedListTvShow: PagedList<DataLocalTvShow>

    @Before
    fun setUp() {
        viewModelMovie = MovieViewModel(dataRepository)
        viewModelTvShow = TvShowViewModel(dataRepository)
        viewModelDetailMov = DetailViewModel(dataRepository)
    }

    @Test
    fun getMovie() {
        val dummyData = pagedListMovie
        val movies = MutableLiveData<Resource<PagedList<DataLocalMovie>>>()
        movies.value = Resource.success(dummyData)
        `when`(dataRepository.getMovieAsPaged(SortUtils.NEWEST)).thenReturn(movies)
        val result = viewModelMovie?.listMoviePaged(SortUtils.NEWEST)
        verify(dataRepository).getMovieAsPaged(SortUtils.NEWEST)

        val expectedValue = movies.value
        val actualValue = viewModelMovie?.listMoviePaged(SortUtils.NEWEST)?.value

        assertNotNull(result)
        assertEquals(19, DummyData.getDummyRemoteMovie().size)
        assertEquals(expectedValue, actualValue)

        viewModelMovie?.listMoviePaged(SortUtils.NEWEST)?.observeForever(observerMovies)
        verify(observerMovies).onChanged(movies.value)
    }

    @Test
    fun getTvShow() {
        val dummyData = pagedListTvShow
        val tvShow = MutableLiveData<Resource<PagedList<DataLocalTvShow>>>()
        tvShow.value = Resource.success(dummyData)
        `when`(dataRepository.getTvShowAsPaged(SortUtils.NEWEST)).thenReturn(tvShow)
        val result = viewModelTvShow?.listTvShowPaged(SortUtils.NEWEST)
        verify(dataRepository).getTvShowAsPaged(SortUtils.NEWEST)

        val expectedValue = tvShow.value
        val actualValue = viewModelTvShow?.listTvShowPaged(SortUtils.NEWEST)?.value

        assertNotNull(result)
        assertEquals(19, DummyData.getDummyRemoteTvShows().size)
        assertEquals(expectedValue, actualValue)

        viewModelTvShow?.listTvShowPaged(SortUtils.NEWEST)?.observeForever(observerTvShows)
        verify(observerTvShows).onChanged(tvShow.value)
    }

    @Test
    fun getDetailMovie() {
        val dummyData = Resource.success(DummyData.getMovieDetail())
        val movie = MutableLiveData<Resource<LocalDetailMovie>>()
        movie.value = dummyData
        viewModelDetailMov?.setDetailSelectedMovie(movieId!!)

        `when`(dataRepository.getDetailMovie(movieId!!)).thenReturn(movie)
        val result = viewModelDetailMov?.getMovie()?.value?.data
        verify(dataRepository).getDetailMovie(movieId)

        assertNotNull(result)

        assertEquals(listMovie.movieId, result?.movieId)
        assertEquals(listMovie.titleMovie, result?.titleMovie)
        assertEquals(listMovie.imagePoster, result?.imagePoster)
        assertEquals("4.8", result?.rating)
        assertEquals("Released", result?.genre)
        assertEquals("2022-01-13", result?.releaseDate)
        assertEquals("94", result?.time)
        assertEquals("Mystery writer Grace Miller has killer instincts when it comes to motive - and she'll need every bit of expertise to help solve her sister's murder.", result?.synopsis)

        viewModelDetailMov?.getMovie()?.observeForever(observerDetailMovie)
        verify(observerDetailMovie).onChanged(dummyData)
    }

    @Test
    fun getDetailTvShow() {
        val dummyData = Resource.success(DummyData.getTvShowDetail())
        val tvShow = MutableLiveData<Resource<LocalDetailTvShow>>()
        tvShow.value = dummyData
        viewModelDetailMov?.setDetailSelectedTvShow(tvShowId!!)

        `when`(dataRepository.getDetailTvShow(tvShowId!!)).thenReturn(tvShow)
        val result = viewModelDetailMov?.getTvShow()?.value?.data
        verify(dataRepository).getDetailTvShow(tvShowId)

        assertNotNull(result)

        assertEquals(listTvShows.tvShowId, result?.tvShowId)
        assertEquals(listTvShows.titleTvShow, result?.titleTvShow)
        assertEquals(listTvShows.imageTvShow, result?.imageTvShow)
        assertEquals("0.0", result?.rating)
        assertEquals("Returning Series", result?.status)
        assertEquals("2002-09-08", result?.releaseDate)
        assertEquals("135", result?.time)
        assertEquals("Big Brother Famosos is the celebrity version of Big Brother Portugal.", result?.synopsis)

        viewModelDetailMov?.getTvShow()?.observeForever(observerDetailTv)
        verify(observerDetailTv).onChanged(dummyData)
    }

}