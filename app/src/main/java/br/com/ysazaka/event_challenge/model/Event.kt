package br.com.ysazaka.event_challenge.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
data class Event (

    @SerializedName("people")
    val peopleList: List<People>,

    @SerializedName("date")
    val date: Double,

    @SerializedName("description")
    val description: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("longitude")
    val longitude: Double,

    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("price")
    val price: Double,

    @SerializedName("title")
    val title: String,

    @SerializedName("id")
    val id: String

)
