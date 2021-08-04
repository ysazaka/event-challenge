package br.com.ysazaka.event_challenge.viewmodel

import br.com.ysazaka.event_challenge.network.RemoteResult
import br.com.ysazaka.event_challenge.usecase.CheckinInterestedPersonOnTheEventUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Glauco Sazaka on 03/08/2021.
 */
@ExperimentalCoroutinesApi
class CheckinInterestedPersonOnTheEventViewModelTest : BaseViewModelTest<CheckinInterestedPersonOnTheEventViewModel>() {

    override lateinit var viewModel: CheckinInterestedPersonOnTheEventViewModel

    private val checkinInterestedPersonOnTheEventUseCase = mockk<CheckinInterestedPersonOnTheEventUseCase>()

    override fun initialize() {
        viewModel = CheckinInterestedPersonOnTheEventViewModel(checkinInterestedPersonOnTheEventUseCase)
    }

    @Test
    fun `need to checkin user on event`() {
        val expectedResult = true

        coEvery { checkinInterestedPersonOnTheEventUseCase.execute(any()) } returns RemoteResult.Success(
            expectedResult
        )

        runBlocking {
            viewModel.checkinInterestedPersonOnTheEvent("1", "Fulano", "fulano@testmail.com")

            assertEquals(expectedResult, viewModel.checkinSuccess.value)
            coVerify(exactly = 1) { checkinInterestedPersonOnTheEventUseCase.execute(any()) }
        }
    }

    @Test
    fun `need to return serverError when getting the event list`() {
        val expectedException = Exception("Erro no servidor")

        coEvery { checkinInterestedPersonOnTheEventUseCase.execute(any()) } returns RemoteResult.ServerError(
            expectedException
        )

        runBlocking {
            viewModel.checkinInterestedPersonOnTheEvent("1", "Fulano", "fulano@testmail.com")

            assertEquals(expectedException, viewModel.serverError.value)
            coVerify(exactly = 1) { checkinInterestedPersonOnTheEventUseCase.execute(any()) }
        }
    }

}