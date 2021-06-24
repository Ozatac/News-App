package com.tunahanozatac.appcentapp.ui.view.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunahanozatac.appcentapp.R
import com.tunahanozatac.appcentapp.databinding.FragmentFavoriteBinding
import com.tunahanozatac.appcentapp.ui.adapter.FavoriteAdapter
import com.tunahanozatac.appcentapp.ui.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    lateinit var topAdapter: FavoriteAdapter
    lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_favorite, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.makeTopApiCall("tr")
        initRecyclerView()
        subSubscribe()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)
    }

    private fun initRecyclerView() {
        layoutManager = LinearLayoutManager(context)
        binding.recyclerViewProfil.layoutManager = layoutManager
        binding.recyclerViewProfil.apply {
            topAdapter = FavoriteAdapter()
            adapter = topAdapter
        }
    }

    private fun subSubscribe() {
        viewModel.getTopListObserver().observe(viewLifecycleOwner, {
            if (it != null) {
                binding.progressProfil.visibility = View.GONE
                topAdapter.updateTopList(it.articles)
            }
        })
    }
}