package com.redmoon.pppjc.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.redmoon.pppjc.common.local.NotesEntity
import com.redmoon.pppjc.interactor.usecase.NotesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: NotesUseCase
): ViewModel() {

    val items = MutableLiveData<List<NotesEntity>>(emptyList())
    private val scope: CoroutineDispatcher = Dispatchers.IO

    init {
        viewModelScope.launch(scope) {
            useCase.getNotesFlow().collect {
                items.postValue(it)
            }
        }
    }

    fun addItem(title: String, description: String) {
        viewModelScope.launch(scope) {
            useCase.insertNote(title = title, description = description)
        }
    }

    fun deleteItem(id: Long) {
        viewModelScope.launch(scope) {
            useCase.deleteNote(id)
        }
    }



}