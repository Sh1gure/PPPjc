package com.redmoon.pppjc.interactor.usecase

import com.redmoon.pppjc.common.repository.repositoryModule
import org.koin.dsl.module

val interactorModule = module {
    factory { NotesUseCase(notesRepository = get()) }
}