package com.redmoon.pppjc.presentation.viewModel

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    single { MainViewModel(get()) }
}