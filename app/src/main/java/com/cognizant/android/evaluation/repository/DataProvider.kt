package com.cognizant.android.evaluation.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

fun <T, A> albumsLiveData(databaseQuery: () -> LiveData<T>,
                          networkCall: suspend () -> Result<A>,
                          saveCallResult: suspend (A) -> Unit): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading())
        val source = databaseQuery.invoke().map { Result.success(it) }
        emitSource(source)
        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Result.Status.SUCCESS) {
            if(responseStatus.data!=null){
            saveCallResult(responseStatus.data)}
        } else if (responseStatus.status == Result.Status.ERROR) {

            if(responseStatus.message!=null){
            emit(Result.error(responseStatus.message))
            emitSource(source)}
        }
    }