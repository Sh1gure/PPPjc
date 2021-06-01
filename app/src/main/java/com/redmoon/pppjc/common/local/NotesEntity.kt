package com.redmoon.pppjc.common.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "cache")
data class NotesEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,

    @ColumnInfo(name = "desc")
    val description: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "timeStamp")
    val timestamp: Long,

)