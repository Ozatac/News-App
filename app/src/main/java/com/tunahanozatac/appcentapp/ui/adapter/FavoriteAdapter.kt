package com.tunahanozatac.appcentapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.tunahanozatac.appcentapp.R
import com.tunahanozatac.appcentapp.data.model.Articles
import com.tunahanozatac.appcentapp.databinding.RecyclerviewTopsrowBinding
import com.tunahanozatac.appcentapp.ui.view.favorite.FavoriteFragmentDirections

class FavoriteAdapter :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private var topList: ArrayList<Articles> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerviewTopsrowBinding>(
            inflater,
            R.layout.recyclerview_topsrow,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(topList[position])
    }

    override fun getItemCount(): Int {
        return topList.size
    }

    class ViewHolder(var binding: RecyclerviewTopsrowBinding) :
        RecyclerView.ViewHolder(binding.root), NewsClickListener<Articles> {
        fun bind(item: Articles) {
            binding.news = item
            binding.listener = this
            binding.executePendingBindings()
        }

        override fun onClick(t: Articles) {
            val action =
                FavoriteFragmentDirections.actionNavigationNotificationsToNewsDetailsFragment(t)
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

    fun updateTopList(updateNewsList: ArrayList<Articles>) {
        topList.addAll(updateNewsList)
        notifyDataSetChanged()
    }
}