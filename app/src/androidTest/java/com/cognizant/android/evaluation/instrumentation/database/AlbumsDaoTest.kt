package com.cognizant.android.evaluation.instrumentation.database

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.cognizant.android.evaluation.models.Albums
import com.cognizant.android.evaluation.storage.AlbumsDao
import com.cognizant.android.evaluation.storage.AppDatabase
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.lessThanOrEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit


class AlbumsDaoTest{

    @Rule
    @JvmField
     val countingTaskExecutorRule = CountingTaskExecutorRule()
    private val mockAlbum= Albums(1,"mock data",999)

    private lateinit var database: AppDatabase
    private lateinit var albumDao: AlbumsDao


    @After
    fun closeDb() {
        countingTaskExecutorRule.drainTasks(10, TimeUnit.SECONDS)
        database.close()
    }

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        albumDao = database.albumsDao!!
        runBlocking {
            albumDao.addAlbums(listOf(mockAlbum))
        }
    }
    @Test
    fun getAlbumsFromDB() {
        val list: List<Albums>? = albumDao.getAlbumsList()
        assertThat(list?.size, equalTo(1))
        assertThat(list?.get(0)?.title, equalTo(mockAlbum.title))
    }
    @Test
    fun getAlbumById() {
        assertThat(albumDao.getAnAlbum(mockAlbum.id)?.id ,lessThanOrEqualTo(mockAlbum.id))
    }
}