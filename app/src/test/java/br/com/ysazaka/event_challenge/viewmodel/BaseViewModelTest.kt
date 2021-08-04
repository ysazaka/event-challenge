package br.com.ysazaka.event_challenge.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

/**
 * Created by Glauco Sazaka on 03/08/2021.
 */
@ExperimentalCoroutinesApi
abstract class BaseViewModelTest<ViewModel> {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    abstract fun initialize()
    abstract var viewModel: ViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        initialize()
    }

    @After
    fun tearDown() = Dispatchers.resetMain()

}