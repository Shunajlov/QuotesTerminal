package com.ihavenodomain.quotesterminal.di

import com.ihavenodomain.quotesterminal.BuildConfig
import com.ihavenodomain.quotesterminal.quoteslist.data.api.SecuritiesRemoteApi
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    val module = module {
        factory { provideOkHttpClient() }
        single { provideRetrofit(get()) }
        factory { provideApi(get()) }
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
            .connectTimeout(1, TimeUnit.SECONDS)
            .connectionPool(ConnectionPool(1, 5, TimeUnit.SECONDS))
            .build()

    private fun provideApi(retrofit: Retrofit): SecuritiesRemoteApi = retrofit.create(SecuritiesRemoteApi::class.java)
}