package com.cognizant.android.evaluation.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cognizant.android.evaluation.repository.AlbumsRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@RunWith(JUnit4::class)
class AlbumViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()
    private val repository = mock(AlbumsRepository::class.java)
    private  var viewModel = AlbumsViewModel(repository)

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Test
    fun initTest() {
        MatcherAssert.assertThat(viewModel.albums, CoreMatchers.nullValue())
    }
}