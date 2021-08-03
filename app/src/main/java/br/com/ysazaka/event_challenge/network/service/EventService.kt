package br.com.ysazaka.event_challenge.network.service

import br.com.ysazaka.event_challenge.model.CheckinResult
import br.com.ysazaka.event_challenge.model.Event
import br.com.ysazaka.event_challenge.model.InterestedPerson
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
interface EventService {

    @GET("events")
    suspend fun getEventList(): List<Event>

    @GET("events/{id}")
    suspend fun getSelectedEvent(@Path("id") id: String): Event

    @POST("checkin")
    suspend fun checkinInterestedPersonOnTheEvent(@Body interestedPerson: InterestedPerson): CheckinResult

}