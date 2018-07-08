package com.example.manorma.testapplication

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.manorma.testapplication.api.NetworkCall
import com.example.manorma.testapplication.api.SearchApi
import com.example.manorma.testapplication.model.*
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.fragment_search_detail.*
import kotlinx.android.synthetic.main.fragment_search_view.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchDetailFragment: Fragment() {

    var page: Page? = null
    var apiInterface: SearchApi?=null
    var TAG="SearchSetailFragment"
    var query1 : Query1? = null

    companion object {
        private val ARG_PAGE:String ="arg_page"
        fun newInstance():SearchDetailFragment{
            var fragment =SearchDetailFragment()
            return fragment
        }
        fun newInstance(page:Page?):SearchDetailFragment{
            var bundle = Bundle()
            bundle.putSerializable(ARG_PAGE,page)
            var fragment =SearchDetailFragment()
            fragment.arguments=bundle

            return fragment
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments?.getSerializable(ARG_PAGE) as Page?
        apiInterface = NetworkCall.getClient(this.context!!).create(SearchApi::class.java)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_detail,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        page = arguments?.getSerializable(ARG_PAGE) as Page?
        Log.e(TAG,"page :"+page)

        var pageId = page?.pageid.toString() as String
        Log.e(TAG,"pageid :"+ pageId)
        val call = apiInterface?.fetchFullUrl(pageId)
        call?.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {

                response?.isSuccessful.let {
                    var res = response?.body()?.string()
                    Log.e(TAG,"response is:"+res)
                    val jsonObject = JSONObject(res)
                    val queryObject = jsonObject.getJSONObject("query")
                    val pageObject = queryObject.getJSONObject("pages")
                    val pageIdObject = pageObject.getJSONObject(pageId)
                    val fullUrl= pageIdObject.getString("fullurl")
                    Log.e(TAG,"url is:"+fullUrl)
                    webView.loadUrl(fullUrl)




                }
            }

            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                Log.e(TAG,t.toString())
                call?.cancel()

            }

        })

        webView.webViewClient = object :WebViewClient(){
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(request?.url?.toString())
                return true
            }
        }



    }


}