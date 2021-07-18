package com.ihavenodomain.quotesterminal.di

import com.ihavenodomain.quotesterminal.BuildConfig
import com.ihavenodomain.quotesterminal.quoteslist.data.api.SecuritiesRemoteRestApi
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    val module = module {
        factory { provideOkHttpClient() }
        single { provideRetrofit(get()) }
        factory { provideRestApi(get()) }
    }

    private fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BuildConfig.apiEndpoint)
            .build()

    private fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(1, 5, TimeUnit.SECONDS))
            .build()

    private fun provideRestApi(retrofit: Retrofit): SecuritiesRemoteRestApi =
        retrofit.create(SecuritiesRemoteRestApi::class.java)

    private fun provideWebSocket(client: OkHttpClient, listener: WebSocketListener): WebSocket {
        val request = Request.Builder()
            .url(BuildConfig.socketEndpoint)
            .build()

        return client.newWebSocket(request, listener)
    }
}