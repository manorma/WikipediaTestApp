package com.example.manorma.testapplication.api

import com.example.manorma.testapplication.model.SearchPageResponse
import com.example.manorma.testapplication.model.SearchResponse
import okhttp3.ResponseBody
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("api.php?action=query&format=json&prop=pageimages%7Cpageterms&generator=prefixsearch&redirects=1&formatversion=2&piprop=thumbnail&pithumbsize=50&pilimit=10&wbptterms=description&gpslimit=10")
    fun fetchSearchResult(@Query("gpssearch") searchString: String): Call<SearchResponse>

    @GET("api.php?action=query&prop=info&inprop=url&format=json")
    fun fetchFullUrl(@Query("pageids") pageid:String):Call<ResponseBody>
}