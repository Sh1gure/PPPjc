package com.redmoon.pppjc.interactor.usecase

import com.redmoon.pppjc.common.local.NotesEntity
import com.redmoon.pppjc.common.repository.NotesRepository
import kotlinx.coroutines.flow.Flow

class NotesUseCase(
    private val notesRepository: NotesRepository
) {

    fun getNotesFlow(): Flow<List<NotesEntity>> = notesRepository.getNotesFlow()

    fun deleteNote(id: Int) {
        notesRepository.delete(id)
    }

    fun insertNote(title: String, description: String) {
        notesRepository.insertNote(NotesEntity(
            title = title,
            description = description,
            timestamp = System.currentTimeMillis()
        ))
    }

}