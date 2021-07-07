package com.cognizant.android.evaluation.storage


import androidx.room.Database
import androidx.room.RoomDatabase
import com.cognizant.android.evaluation.models.Albums

@Database(
    entities = [Albums::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val albumsDao: AlbumsDao?
}
