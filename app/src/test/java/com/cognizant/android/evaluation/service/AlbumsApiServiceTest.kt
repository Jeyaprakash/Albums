package com.cognizant.android.evaluation.service

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cognizant.android.evaluation.network.AlbumApiService
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class AlbumsApiServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: AlbumApiService

    private lateinit var mockWebServer: MockWebServer


    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlbumApiService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun getAlbumsResponse() {
        runBlocking {
            mockResponse("mock-albums.json")
            val resultResponse = service.getAlbums()
            val albums = resultResponse.body()
           assertThat(albums?.size, CoreMatchers.`is`(100))
        }
    }

    private fun mockResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val source  = javaClass.classLoader
            .getResourceAsStream(fileName).source().buffer()

        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(mockResponse.setBody(
            source.readString(Charsets.UTF_8))
        )
    }


}