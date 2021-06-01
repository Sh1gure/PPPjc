package com.redmoon.pppjc.common.repository

import com.redmoon.pppjc.common.local.NotesEntity
import kotlinx.coroutines.flow.Flow

interface NotesRepository {
    fun getNotesFlow(): Flow<List<NotesEntity>>
    fun insertNote(notes: NotesEntity)
    fun delete(id: Long)
}