package br.com.ysazaka.event_challenge.network.service

import br.com.ysazaka.event_challenge.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Glauco Sazaka on 01/08/2021.
 */
abstract class BaseServiceFactory<T>(baseUrl: String) {

    protected val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
    }

    fun create(clazz: Class<T>): T = retrofit.build().create(clazz)

    protected fun createOkHttpClient(): OkHttpClient.Builder {

        val builder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(
                HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BASIC)
            )
        }

        return builder
    }

}