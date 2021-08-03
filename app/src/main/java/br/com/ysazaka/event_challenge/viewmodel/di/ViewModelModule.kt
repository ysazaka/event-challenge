package br.com.ysazaka.event_challenge.viewmodel.di

import br.com.ysazaka.event_challenge.viewmodel.CheckinInterestedPersonOnTheEventViewModel
import br.com.ysazaka.event_challenge.viewmodel.GetEventListViewModel
import br.com.ysazaka.event_challenge.viewmodel.GetSelectedEventViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
fun createViewModelModule() = module {
    viewModel { GetEventListViewModel(get()) }
    viewModel { GetSelectedEventViewModel(get()) }
    viewModel { CheckinInterestedPersonOnTheEventViewModel(get()) }
}