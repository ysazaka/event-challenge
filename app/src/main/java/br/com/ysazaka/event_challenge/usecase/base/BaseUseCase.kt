package br.com.ysazaka.event_challenge.usecase.base

import br.com.ysazaka.event_challenge.network.*
import retrofit2.HttpException
import java.net.SocketTimeoutException


/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
abstract class BaseUseCase<T, R> {

    suspend fun request(apiCall: suspend () -> T): RemoteResult<R> {
        return try {
            RemoteResult.Success(mapResult(apiCall.invoke()))
        } catch (exception: Exception) {
            handleErrors(exception)
        }
    }

    protected abstract fun mapResult(result: T): R

    protected fun handleErrors(exception: Exception): RemoteResult<Nothing> {
        if (exception is HttpException) {
            return when (exception.code()) {
                STATUS_CODE_NO_CONTENT -> RemoteResult.Empty
                STATUS_CODE_NOT_FOUND -> RemoteResult.NotFound
                else -> RemoteResult.ServerError(exception)
            }
        } else if (exception is SocketTimeoutException) {
            return RemoteResult.ServerError(Exception("Ocorreu um erro no aplicativo. Tente novamente mais tarde."))
        }

        return RemoteResult.ServerError(exception)
    }

}