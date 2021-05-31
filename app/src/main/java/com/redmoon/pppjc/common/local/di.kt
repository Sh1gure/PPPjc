package com.redmoon.pppjc.common.local

import androidx.room.Room
import org.koin.dsl.module

val databaseModule = module {

    single<NotesDatabase> {
        Room.databaseBuilder(get(), NotesDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

}