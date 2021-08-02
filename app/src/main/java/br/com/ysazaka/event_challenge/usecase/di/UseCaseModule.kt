package br.com.ysazaka.event_challenge.usecase.di

import br.com.ysazaka.event_challenge.usecase.GetEventListUseCase
import br.com.ysazaka.event_challenge.usecase.GetSelectedEventUseCase
import org.koin.dsl.module

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
fun createUseCaseModule() = module {
    single { GetEventListUseCase(get()) }
    single { GetSelectedEventUseCase(get()) }
}