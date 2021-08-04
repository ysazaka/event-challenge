package br.com.ysazaka.event_challenge

import android.app.Application
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import br.com.ysazaka.event_challenge.di.EventModules
import org.koin.core.component.KoinComponent

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
class BaseApplication: Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        MultiDex.install(this)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        EventModules.initialize(this)
    }

}