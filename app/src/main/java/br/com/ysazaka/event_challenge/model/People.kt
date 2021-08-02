package br.com.ysazaka.event_challenge.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
data class People (

    @SerializedName("id")
    val id: String,

    @SerializedName("eventId")
    val eventId: String,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("picture")
    val picture: String
    
)