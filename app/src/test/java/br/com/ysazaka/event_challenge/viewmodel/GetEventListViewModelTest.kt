package br.com.ysazaka.event_challenge.viewmodel

import br.com.ysazaka.event_challenge.dto.EventDto
import br.com.ysazaka.event_challenge.dto.PeopleDto
import br.com.ysazaka.event_challenge.network.RemoteResult
import br.com.ysazaka.event_challenge.usecase.GetEventListUseCase
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
class GetEventListViewModelTest : BaseViewModelTest<GetEventListViewModel>() {

    override lateinit var viewModel: GetEventListViewModel

    private val getEventListUseCase = mockk<GetEventListUseCase>()

    override fun initialize() {
        viewModel = GetEventListViewModel(getEventListUseCase)
    }

    @Test
    fun `need to get the event list`() {
        val mockPeopleList: List<PeopleDto> = listOf(
            PeopleDto(
                "1",
                "1",
                "Nome teste",
                "Foto teste"
            )
        )

        val expectedResult: List<EventDto> = listOf(
            EventDto(
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
        )

        val expectedResultSize = 1

        coEvery { getEventListUseCase.execute() } returns RemoteResult.Success(
            expectedResult
        )

        runBlocking {
            viewModel.getEventList()

            Assert.assertEquals(expectedResult, viewModel.eventListResult.value)
            Assert.assertEquals(expectedResultSize, viewModel.eventListResult.value?.size)
            coVerify(exactly = 1) { getEventListUseCase.execute() }
        }
    }

    @Test
    fun `need to return serverError when getting the event list`() {
        val expectedException = Exception("Erro no servidor")

        coEvery { getEventListUseCase.execute() } returns RemoteResult.ServerError(
            expectedException
        )

        runBlocking {
            viewModel.getEventList()

            Assert.assertEquals(expectedException, viewModel.serverError.value)
            coVerify(exactly = 1) { getEventListUseCase.execute() }
        }
    }

}