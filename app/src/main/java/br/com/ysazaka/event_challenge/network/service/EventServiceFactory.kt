package br.com.ysazaka.event_challenge.network.service

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
class EventServiceFactory(
    baseUrl: String
) : BaseServiceFactory<EventService>(baseUrl) {

    init {
        retrofit.build()
    }

}