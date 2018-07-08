package com.example.manorma.testapplication.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class SearchResponse(
    val batchcomplete: Boolean,
    val continue1: Continue,
    val query: Query
)

data class Continue(
    val gpsoffset: Int,
    val continue1: String
)

data class Query(
    val redirects: List<Redirect>,
    val pages: List<Page>
)

data class Redirect(
    val index: Int,
    val from: String,
    val to: String
)

data class Page(
    val pageid: Int,
    val ns: Int,
    val title: String,
    val index: Int,
    val thumbnail: Thumbnail,
    val terms: Terms
) : Serializable

data class Thumbnail(
    val source: String,
    val width: Int,
    val height: Int
) :Serializable

data class Terms(
    val description: List<String>
):Serializable


