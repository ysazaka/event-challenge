package br.com.ysazaka.event_challenge.network.service

import android.content.Context
import br.com.ysazaka.event_challenge.R
import org.koin.dsl.module

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */

fun createServiceModule(context: Context) = module {
    val baseUrl = context.getString(R.string.base_url)

    single { EventServiceFactory(baseUrl) }
    single { buildService(get<EventServiceFactory>()) }
}

inline fun <reified T> buildService(factory: BaseServiceFactory<T>): T {
    return factory.create(T::class.java)
}