package com.dedesaepulloh.tribunnews.utils

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.dedesaepulloh.tribunnews.BuildConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConfig @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }
    val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val username = BuildConfig.usernameAuth
            val password = BuildConfig.passwordAuth
            val authToken = Credentials.basic(username, password)
            val request = chain.request()
            val queryParams = request.url.newBuilder()
                .build()
            val newRequest = request.newBuilder()
                .url(queryParams)
                .addHeader(
                    "Authorization",
                    authToken
                )
                .build()
            chain.proceed(newRequest)
        }
        .addInterceptor(loggingInterceptor)
        .addInterceptor(ChuckerInterceptor(context))
        .readTimeout(600, TimeUnit.SECONDS)
        .connectTimeout(600, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()
}