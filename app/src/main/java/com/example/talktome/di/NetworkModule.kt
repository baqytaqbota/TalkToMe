package com.example.talktome.di

import com.example.talktome.common.network.ApiService
import com.example.talktome.common.network.interceptors.HeaderInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 60L

val networkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), "http://185.5.206.85/api/v1/") }

    single { createOkHttpClient() }

}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addNetworkInterceptor(HeaderInterceptor())
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(createGson()))
        .baseUrl(url)
        .client(okHttpClient).build()
}

fun createGson(): Gson = GsonBuilder()
    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    .setLenient().create()

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}
