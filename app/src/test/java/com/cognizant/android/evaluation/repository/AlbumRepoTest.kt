package com.cognizant.android.evaluation.repository


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cognizant.android.evaluation.network.AlbumApiService
import com.cognizant.android.evaluation.network.ApiHelper
import com.cognizant.android.evaluation.storage.AlbumsDao
import com.cognizant.android.evaluation.storage.AppDatabase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class AlbumRepoTest {
    private lateinit var repository: AlbumsRepository
    private val dao = mock(AlbumsDao::class.java)
    private val service = mock(AlbumApiService::class.java)
    private val apiHelperService = ApiHelper(service)
    private lateinit var db:AppDatabase

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        db = mock(AppDatabase::class.java)
        `when`(db.albumsDao).thenReturn(dao)
        `when`(db.runInTransaction(ArgumentMatchers.any())).thenCallRealMethod()
        repository = AlbumsRepository(dao, apiHelperService)
    }
    @Test
    fun getAlbumsFromRoom() {
        runBlocking {
            repository.albums
            verify(dao, never()).getAlbumsAsLiveData()
            verifyZeroInteractions(dao)
        }
    }

}