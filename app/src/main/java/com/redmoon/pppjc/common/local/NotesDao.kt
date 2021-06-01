package com.redmoon.pppjc.common.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Insert
    fun insertNotes(data: NotesEntity)

    @Query("DELETE FROM cache WHERE id = :id")
    fun deleteNote(id: Long)

    @Query("SELECT * FROM cache")
    fun getNotes(): Flow<List<NotesEntity>>

}