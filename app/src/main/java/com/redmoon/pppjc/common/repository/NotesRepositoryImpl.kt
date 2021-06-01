package com.redmoon.pppjc.common.repository

import com.redmoon.pppjc.common.local.NotesDatabase
import com.redmoon.pppjc.common.local.NotesEntity
import kotlinx.coroutines.flow.Flow

class NotesRepositoryImpl(
    private val notesDao: NotesDatabase
) : NotesRepository {
    override fun getNotesFlow(): Flow<List<NotesEntity>> {
        return notesDao.DAO().getNotes()
    }

    override fun insertNote(notes: NotesEntity) {
        notesDao.DAO().insertNotes(notes)
    }

    override fun delete(id: Long) {
        notesDao.DAO().deleteNote(id)
    }
}