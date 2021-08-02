package br.com.ysazaka.event_challenge.usecase

import br.com.ysazaka.event_challenge.dto.EventDto
import br.com.ysazaka.event_challenge.dto.PeopleDto
import br.com.ysazaka.event_challenge.model.Event
import br.com.ysazaka.event_challenge.network.service.EventService
import br.com.ysazaka.event_challenge.usecase.base.BaseUseCase

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
class GetEventListUseCase(
    private val eventService: EventService
) : BaseUseCase<List<Event>, List<EventDto>>() {

    suspend fun execute() =
        request {
            eventService.getEventList()
        }

    override fun mapResult(result: List<Event>): List<EventDto> {
        return result.map { event ->
            EventDto(
                event.peopleList.map { people ->
                    PeopleDto(
                        people.id,
                        people.eventId,
                        people.name,
                        people.picture
                    )
                },
                event.date,
                event.description,
                event.image,
                event.longitude,
                event.latitude,
                event.price,
                event.title,
                event.id
            )
        }
    }

}