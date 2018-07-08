package com.example.manorma.testapplication

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.manorma.testapplication.model.Page
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list_view.view.*


class SearchAdapter(var pages:List<Page>,var clickListener: View.OnClickListener): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {



    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        var page = pages.get(position)
        holder?.tvTitle?.text=page.title
        holder?.tvDesc?.text= page.terms?.description?.get(0)
        holder?.itemView.tag=position
        Picasso.get()
                .load(page.thumbnail?.source)
                .resize(50,45)
                .into(holder?.ivImage)



    }

    override fun getItemCount(): Int {
        Log.d("Adapter","size of page :"+pages.size)
        return pages.size

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_list_view,parent,false))

    }



    inner class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvTitle = itemView.tvTitle
        var ivImage = itemView.ivImage
        var tvDesc = itemView.tvDescription
        init {
            if(clickListener != null){
                itemView.setOnClickListener(clickListener)
            }

        }

    }

}


