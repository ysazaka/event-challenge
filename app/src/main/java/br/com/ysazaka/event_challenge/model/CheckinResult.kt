package br.com.ysazaka.event_challenge.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Glauco Sazaka on 03/08/2021.
 */
data class CheckinResult (
    @SerializedName("code")
    val code: String
)