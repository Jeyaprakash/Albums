package com.cognizant.android.evaluation.network

import com.cognizant.android.evaluation.repository.Result
import com.cognizant.android.evaluation.testing.OpenForTesting
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
class ApiHelper @Inject constructor(private val apiService: AlbumApiService)
{
     suspend fun getAlbums() = getResult { apiService.getAlbums() }

    private suspend fun <T> getResult(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Result.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }
    private fun <T> error(message: String): Result<T> {
        return Result.error( "Network call has failed for a following reason: $message",data = null)
    }

}