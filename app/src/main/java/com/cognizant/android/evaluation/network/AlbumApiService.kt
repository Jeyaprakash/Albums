package com.cognizant.android.evaluation.network

import com.cognizant.android.evaluation.models.Albums
import retrofit2.Response
import retrofit2.http.GET

interface AlbumApiService {
    @GET("albums")
    suspend fun getAlbums(): Response<List<Albums>>

}