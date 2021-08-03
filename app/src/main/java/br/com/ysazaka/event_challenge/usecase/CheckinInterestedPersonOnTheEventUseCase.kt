package br.com.ysazaka.event_challenge.usecase

import br.com.ysazaka.event_challenge.model.CheckinResult
import br.com.ysazaka.event_challenge.model.InterestedPerson
import br.com.ysazaka.event_challenge.network.STATUS_CODE_SUCCESS
import br.com.ysazaka.event_challenge.network.service.EventService
import br.com.ysazaka.event_challenge.usecase.base.BaseUseCase
import io.reactivex.Completable

/**
 * Created by Glauco Sazaka on 03/08/2021.
 */
class CheckinInterestedPersonOnTheEventUseCase (
    private val eventService: EventService
) : BaseUseCase<CheckinResult, Boolean>() {

    suspend fun execute(interestedPerson: InterestedPerson) =
        request {
            eventService.checkinInterestedPersonOnTheEvent(interestedPerson)
        }

    override fun mapResult(result: CheckinResult): Boolean {
        return result.code == STATUS_CODE_SUCCESS
    }

}