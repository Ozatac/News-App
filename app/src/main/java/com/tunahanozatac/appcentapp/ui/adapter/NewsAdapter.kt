package com.tunahanozatac.appcentapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tunahanozatac.appcentapp.R
import com.tunahanozatac.appcentapp.data.model.Articles
import com.tunahanozatac.appcentapp.databinding.RecyclerviewNewsrowBinding

class NewsAdapter :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>(), NewsClickListener {

    private var newsList: ArrayList<Articles> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerviewNewsrowBinding>(
            inflater,
            R.layout.recyclerview_newsrow,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(newsList[position])
        holder.binding.listener = this
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class ViewHolder(var binding: RecyclerviewNewsrowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Articles) {
            binding.news = item
            binding.executePendingBindings()
        }
    }

    fun updateList(updateNewsList: ArrayList<Articles>) {
        newsList.clear()
        newsList = updateNewsList
        notifyDataSetChanged()
    }

    override fun onClick(v: View) {
    }
}