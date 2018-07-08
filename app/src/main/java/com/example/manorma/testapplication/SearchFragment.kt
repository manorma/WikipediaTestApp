package com.example.manorma.testapplication

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.manorma.testapplication.api.NetworkCall
import com.example.manorma.testapplication.model.Page
import com.example.manorma.testapplication.model.SearchResponse

import kotlinx.android.synthetic.main.fragment_search_view.*

import android.view.View.OnFocusChangeListener
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import com.example.manorma.testapplication.api.SearchApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.provider.BaseColumns
import android.database.MatrixCursor
import android.widget.CursorAdapter
import android.widget.SimpleCursorAdapter
import com.example.manorma.testapplication.R.id.searchView




class SearchFragment : Fragment() ,View.OnClickListener{
    var TAG :String? = "SearchFragment"
    var apiInterface:SearchApi?=null
    lateinit var searchScreenListener:SearchScreenListener


    override fun onClick(v: View?) {
        val pos  = v?.tag as Int
        println("position is "+v?.tag)
        searchScreenListener.navigateToDetaiScreen(pages?.get(pos))

    }

    var searchAdapter:SearchAdapter?=null
    var pages : List<Page>?= emptyList()

    companion object {
        fun newInstance():SearchFragment{
            val fragment = SearchFragment()
            return fragment;

        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        searchScreenListener = context as SearchScreenListener
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_view,container,false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvList.layoutManager = LinearLayoutManager(activity)
        searchAdapter = SearchAdapter(pages!!,this)
        rvList.adapter=searchAdapter
        searchView.queryHint ="Type Something to search"
        searchView.setOnClickListener(searchListener)
        apiInterface = NetworkCall.getClient(this.context!!).create(SearchApi::class.java)


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false

            }

            override fun onQueryTextSubmit(query: String): Boolean {
                fetchSearchResult(query);
                return true

            }

        })



    }

    private fun fetchSearchResult(query:String) {
        val call = apiInterface?.fetchSearchResult(query)
        call?.enqueue(object :Callback<SearchResponse>{
            override fun onResponse(call: Call<SearchResponse>?, response: Response<SearchResponse>?) {
                Log.e(TAG,"response is:"+response?.body())
                response?.isSuccessful.let {
                    pages = response?.body()?.query?.pages
                    Log.e(TAG,"page is:"+pages)
                    searchAdapter = SearchAdapter(pages!!,this@SearchFragment)
                    rvList.adapter=searchAdapter
                    searchAdapter!!.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                Log.e(TAG,t.toString())
                call?.cancel()

            }

        })
    }

    val searchListener = View.OnClickListener { view ->
        val imm :InputMethodManager= context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null) {
            imm!!.showSoftInput(view, 0)
        }
    }

    private fun showInputMethod(view: View) {
        val imm :InputMethodManager= context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm != null) {
            imm!!.showSoftInput(view, 0)
        }
    }



}