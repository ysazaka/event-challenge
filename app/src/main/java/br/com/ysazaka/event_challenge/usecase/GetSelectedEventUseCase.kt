package br.com.ysazaka.event_challenge.usecase

import br.com.ysazaka.event_challenge.dto.EventDto
import br.com.ysazaka.event_challenge.dto.PeopleDto
import br.com.ysazaka.event_challenge.model.Event
import br.com.ysazaka.event_challenge.network.service.EventService
import br.com.ysazaka.event_challenge.usecase.base.BaseUseCase

/**
 * Created by Glauco Sazaka on 02/08/2021.
 */
class GetSelectedEventUseCase (
    private val eventService: EventService
) : BaseUseCase<Event, EventDto>() {

    suspend fun execute(eventId: String) =
        request {
            eventService.getSelectedEvent(eventId)
        }

    override fun mapResult(result: Event): EventDto {
        return EventDto(
            result.peopleList.map { people ->
                PeopleDto(
                    people.id,
                    people.eventId,
                    people.name,
                    people.picture
                )
            },
            result.date,
            result.description,
            result.image,
            result.longitude,
            result.latitude,
            result.price,
            result.title,
            result.id,
        )
    }

}