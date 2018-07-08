package com.example.manorma.testapplication.model


data class SearchPageResponse(
    val batchcomplete: String,
    val query: Query1
){
    override fun toString(): String {
        return "SearchPageResponse(batchcomplete='$batchcomplete', query=$query)"
    }
}

data class Query1(
    val pages:String
){
    override fun toString(): String {
        return "Query1(pages='$pages')"
    }
}



//data class Pages(
//    val pageid: PageInfo
//)
//
//data class PageInfo(
//    val pageid: Int,
//    val ns: Int,
//    val title: String,
//    val contentmodel: String,
//    val pagelanguage: String,
//    val pagelanguagehtmlcode: String,
//    val pagelanguagedir: String,
//    val touched: String,
//    val lastrevid: Int,
//    val length: Int,
//    val fullurl: String,
//    val editurl: String,
//    val canonicalurl: String
//)