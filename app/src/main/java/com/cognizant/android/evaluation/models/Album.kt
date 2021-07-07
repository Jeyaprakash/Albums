package com.cognizant.android.evaluation.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class Albums(@PrimaryKey(autoGenerate = true) val id: Int
                      , val title: String?, val userId: Int)
