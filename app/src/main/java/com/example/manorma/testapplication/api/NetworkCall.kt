package com.example.manorma.testapplication.api

import android.content.Context
import com.example.manorma.testapplication.model.SearchResponse
import okhttp3.Cache

import okhttp3.OkHttpClient


import java.util.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


class NetworkCall {

    companion object {
        lateinit var retrofit: Retrofit

        fun getClient(context:Context) : Retrofit{
            val url ="https://en.wikipedia.org//w/"
            val logging = HttpLoggingInterceptor()
            val httpCacheDirectory = File(context.getCacheDir(), "responses")
            val cacheSize :Long = 10 * 1024 * 1024 // 10 MiB
            val cache = Cache(httpCacheDirectory, cacheSize)
            // set your desired log level
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(logging).build()

            // add your other interceptors …
            // add logging as last interceptor
            retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient)
                    .build()

            return retrofit


        }
    }


}