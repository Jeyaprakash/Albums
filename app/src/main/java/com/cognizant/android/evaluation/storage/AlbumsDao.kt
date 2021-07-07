package com.cognizant.android.evaluation.storage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.cognizant.android.evaluation.models.Albums
import com.cognizant.android.evaluation.testing.OpenForTesting

@Dao
@OpenForTesting
interface AlbumsDao {

    @Query("SELECT * FROM albums order by title ASC")
    fun getAlbumsList(): List<Albums>?

    @Query("SELECT * FROM albums order by title ASC")
    fun getAlbumsAsLiveData(): LiveData<List<Albums?>?>

    @Query("select * from albums where id = :id")
    fun getAnAlbum(id: Int?): Albums?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAlbums(album: List<Albums?>?)

    @Update
    suspend fun updateAlbums(album: List<Albums?>?): Int

    @Delete
    suspend fun removeAlbum(album: List<Albums?>?): Int

}