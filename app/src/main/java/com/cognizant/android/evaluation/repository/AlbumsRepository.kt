package com.cognizant.android.evaluation.repository

import com.cognizant.android.evaluation.network.ApiHelper
import com.cognizant.android.evaluation.storage.AlbumsDao
import com.cognizant.android.evaluation.testing.OpenForTesting
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@OpenForTesting
class AlbumsRepository  @Inject constructor(private val localDb:AlbumsDao,private val apiHelper: ApiHelper) {
    val albums = albumsLiveData(
        databaseQuery = { localDb.getAlbumsAsLiveData() },
        networkCall = { apiHelper.getAlbums() },
        saveCallResult = { localDb.addAlbums(it) })

}