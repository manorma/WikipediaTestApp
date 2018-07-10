package com.example.manorma.testapplication.api

import android.content.Context
import com.example.manorma.testapplication.model.SearchResponse
import com.example.manorma.testapplication.util.AppUtil
import okhttp3.Cache
import okhttp3.Interceptor

import okhttp3.OkHttpClient
import okhttp3.Response


import java.util.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File


class NetworkCall {

    companion object {
        lateinit var retrofit: Retrofit



        fun getClient(context:Context) : Retrofit {
            val url ="https://en.wikipedia.org//w/"
            val logging = HttpLoggingInterceptor()

            var REWRITE_CACHE_CONTROL_INTERCEPTOR = Interceptor{
                chain ->
                var response:Response? = chain?.proceed(chain?.request())
                if(AppUtil.isNetworkAvailable(context)) {
                    response?.newBuilder()?.header("Cache-Control", "public, max-age=" + 60)?.build()
                }
                else{
                    var maxStale= 60*60*24*28
                    response?.newBuilder()?.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)?.build()
                }



            }
            val httpCacheDirectory = File(context.getCacheDir(), "responses")
            val cacheSize :Long = 10 * 1024 * 1024 // 10 MiB
            val cache = Cache(httpCacheDirectory, cacheSize)
            // set your desired log level
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient().newBuilder()
            httpClient.networkInterceptors().add(REWRITE_CACHE_CONTROL_INTERCEPTOR)
            httpClient.addInterceptor(logging)
            httpClient.cache(cache)





            // add your other interceptors …
            // add logging as last interceptor
            retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()

            return retrofit


        }
    }


}