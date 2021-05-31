package com.redmoon.pppjc.common.local

import androidx.room.Database
import androidx.room.RoomDatabase

const val DB_NAME = "app_db"

@Database(
    entities = [NotesEntity::class],
    version = 3,
    exportSchema = false
)

abstract class NotesDatabase : RoomDatabase(){

    abstract fun DAO(): NotesDao

}