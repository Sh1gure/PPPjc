package com.redmoon.pppjc

import android.app.Application
import com.redmoon.pppjc.common.local.databaseModule
import com.redmoon.pppjc.common.repository.repositoryModule
import com.redmoon.pppjc.interactor.usecase.interactorModule
import com.redmoon.pppjc.presentation.viewModel.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationPP : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ApplicationPP)
            modules(
                listOf(
                    databaseModule,
                    repositoryModule,
                    interactorModule,
                    presentationModule
                )
            )
        }
    }
}