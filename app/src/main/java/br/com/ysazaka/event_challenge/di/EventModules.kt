package br.com.ysazaka.event_challenge.di

import android.content.Context
import br.com.ysazaka.event_challenge.network.service.createServiceModule
import br.com.ysazaka.event_challenge.usecase.di.createUseCaseModule
import br.com.ysazaka.event_challenge.viewmodel.di.createViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
object EventModules {

    @JvmStatic
    fun initialize(context: Context) {

        startKoin {
            androidLogger()
            androidContext(context)
            modules(
                createServiceModule(context),
                createUseCaseModule(),
                createViewModelModule()
            )
        }
    }

}