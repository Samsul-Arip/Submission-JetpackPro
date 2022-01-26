package com.samsul.moviecatalogue.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.samsul.moviecatalogue.DummyData
import com.samsul.moviecatalogue.data.local.entity.DataLocalMovie
import com.samsul.moviecatalogue.data.local.entity.DataLocalTvShow
import com.samsul.moviecatalogue.data.repository.DataRepository
import com.samsul.moviecatalogue.ui.detail.DetailViewModel
import com.samsul.moviecatalogue.ui.fragment.bookmark.BookmarkViewModel
import com.samsul.moviecatalogue.ui.fragment.movie.MovieViewModel
import com.samsul.moviecatalogue.ui.fragment.tvshow.TvShowViewModel
import com.samsul.moviecatalogue.utils.SortUtils
import com.samsul.moviecatalogue.vo.Resource
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ViewModelBookmarkTest {

    @get:Rule
    var instanceTaskExecutorRule = InstantTaskExecutorRule()

    private var viewModelBookmark: BookmarkViewModel? = null

    private var dataRepository = Mockito.mock(DataRepository::class.java)

    @Mock
    private lateinit var observerTvShows: Observer<PagedList<DataLocalTvShow>>

    @Mock
    private lateinit var observerMovies: Observer<PagedList<DataLocalMovie>>

    @Mock
    private lateinit var pagedListMovie: PagedList<DataLocalMovie>

    @Mock
    private lateinit var pagedListTvShow: PagedList<DataLocalTvShow>

    @Before
    fun setUp() {
        viewModelBookmark = BookmarkViewModel(dataRepository)
    }

    @Test
    fun getBookmarkMovie() {
        val dummyData = pagedListMovie
        val movies = MutableLiveData<PagedList<DataLocalMovie>>()
        movies.value = dummyData
        `when`(dataRepository.getMovieAsPagedBookmark()).thenReturn(movies)
        val result = viewModelBookmark?.getMovieBookmark()
        verify(dataRepository).getMovieAsPagedBookmark()

        val expectedValue = movies.value
        val actualValue = viewModelBookmark?.getMovieBookmark()?.value

        TestCase.assertNotNull(result)
        assertEquals(19, DummyData.getDummyRemoteMovie().size)
        assertEquals(expectedValue, actualValue)

        viewModelBookmark?.getMovieBookmark()?.observeForever(observerMovies)
        verify(observerMovies).onChanged(movies.value)
    }

    @Test
    fun getBookmarkTvShow() {
        val dummyData = pagedListTvShow
        val movies = MutableLiveData<PagedList<DataLocalTvShow>>()
        movies.value = dummyData
        `when`(dataRepository.getTvShowAsPagedBookmark()).thenReturn(movies)
        val result = viewModelBookmark?.getTvShowBookmark()
        verify(dataRepository).getTvShowAsPagedBookmark()

        val expectedValue = movies.value
        val actualValue = viewModelBookmark?.getTvShowBookmark()?.value

        TestCase.assertNotNull(result)
        assertEquals(19, DummyData.getDummyRemoteMovie().size)
        assertEquals(expectedValue, actualValue)

        viewModelBookmark?.getTvShowBookmark()?.observeForever(observerTvShows)
        verify(observerTvShows).onChanged(movies.value)
    }
}