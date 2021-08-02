package br.com.ysazaka.event_challenge.dto


/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
data class EventDto (
    val peopleList: List<PeopleDto>,
    val date: Double,
    val description: String,
    val image: String,
    val longitude: Double,
    val latitude: Double,
    val price: Double,
    val title: String,
    val id: String
)