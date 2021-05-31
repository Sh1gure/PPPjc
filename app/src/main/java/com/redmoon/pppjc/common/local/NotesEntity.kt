package com.redmoon.pppjc.common.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cache")
data class NotesEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = -1,

    @ColumnInfo(name = "desc")
    var description: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "timeStamp")
    var timestamp: Long,

)