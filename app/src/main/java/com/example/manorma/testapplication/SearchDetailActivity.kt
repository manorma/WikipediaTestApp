package com.example.manorma.testapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import com.example.manorma.testapplication.model.Page
import kotlinx.android.synthetic.main.view_toolbar.*

class SearchDetailActivity: AppCompatActivity() {
    var page :Page ?= null

    companion object {

        private val ARG_PAGE = "arg_page"

        fun newIntent(context: Context, page: Page): Intent {
            val intent = Intent(context, SearchDetailActivity::class.java)
            intent.putExtra(ARG_PAGE,page )
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_detail)
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        page = intent.extras.getSerializable(ARG_PAGE) as Page?
        Log.e("SearchSetailActivity","page is "+page)
        openSearchDetailFragment();
    }

    private fun openSearchDetailFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.rlMainContainer, SearchDetailFragment.newInstance(page), SearchDetailFragment.newInstance(page).javaClass.getSimpleName())
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE)
        fragmentTransaction.addToBackStack(SearchDetailFragment.newInstance(page).javaClass.getSimpleName())
        fragmentTransaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home ->{
                onBackPressed();
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}