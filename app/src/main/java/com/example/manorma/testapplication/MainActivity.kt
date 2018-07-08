package com.example.manorma.testapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.example.manorma.testapplication.model.Page
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_toolbar.*

class MainActivity : AppCompatActivity() ,SearchScreenListener{

    override fun navigateToDetaiScreen(page:Page?) {
        var intent =SearchDetailActivity.newIntent(this, page!!)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolBar)
        openSearchFragment();
    }

    private fun openSearchFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.rlMainContainer, SearchFragment.newInstance(), SearchFragment.newInstance().javaClass.getSimpleName())
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_NONE)
        fragmentTransaction.addToBackStack(SearchFragment.newInstance().javaClass.getSimpleName())
        fragmentTransaction.commit()
    }


}
