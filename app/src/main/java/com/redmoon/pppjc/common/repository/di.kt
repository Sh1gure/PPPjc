package com.redmoon.pppjc.common.repository

import org.koin.dsl.module

val repositoryModule = module {
    factory<NotesRepository> {
        NotesRepositoryImpl(get())
    }
}