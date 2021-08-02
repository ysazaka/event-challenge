package br.com.ysazaka.event_challenge.network

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
sealed class RemoteResult<out T> {

    data class Success<out T>(val data: T) : RemoteResult<T>()

    object Empty : RemoteResult<Nothing>()
    object NotFound : RemoteResult<Nothing>()

    data class ServerError(val cause: Exception) : RemoteResult<Nothing>()

}