package br.com.ysazaka.event_challenge.viewmodel

import br.com.ysazaka.event_challenge.dto.EventDto
import br.com.ysazaka.event_challenge.dto.PeopleDto
import br.com.ysazaka.event_challenge.network.RemoteResult
import br.com.ysazaka.event_challenge.usecase.GetSelectedEventUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

/**
 * Created by Glauco Sazaka on 03/08/2021.
 */
@ExperimentalCoroutinesApi
class GetSelectedEventViewModelTest : BaseViewModelTest<GetSelectedEventViewModel>() {

    override lateinit var viewModel: GetSelectedEventViewModel

    private val getSelectedEventUseCase = mockk<GetSelectedEventUseCase>()

    override fun initialize() {
        viewModel = GetSelectedEventViewModel(getSelectedEventUseCase)
    }

    @Test
    fun `need to get an specific event data`() {
        val mockPeopleList: List<PeopleDto> = listOf(
            PeopleDto(
                "1",
                "1",
                "Nome teste",
                "Foto teste"
            )
        )

        val expectedResult = EventDto(
                mockPeopleList,
                1534784400.0,
                "Descrição teste",
                "https://fakeimg.pl/300",
                0.0,
                0.0,
                0.99,
                "Título teste",
                "1"
            )

        coEvery { getSelectedEventUseCase.execute(any()) } returns RemoteResult.Success(
            expectedResult
        )

        runBlocking {
            viewModel.getEventById("1")

            Assert.assertEquals(expectedResult, viewModel.selectedEventResult.value)
            coVerify(exactly = 1) { getSelectedEventUseCase.execute(any()) }
        }
    }

    @Test
    fun `need to return serverError when getting the specific event data`() {
        val expectedException = Exception("Erro no servidor")

        coEvery { getSelectedEventUseCase.execute(any()) } returns RemoteResult.ServerError(
            expectedException
        )

        runBlocking {
            viewModel.getEventById("1")

            Assert.assertEquals(expectedException, viewModel.serverError.value)
            coVerify(exactly = 1) { getSelectedEventUseCase.execute(any()) }
        }
    }

}