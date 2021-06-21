package com.tunahanozatac.appcentapp.ui.view.news

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.tunahanozatac.appcentapp.R
import com.tunahanozatac.appcentapp.data.model.Articles
import com.tunahanozatac.appcentapp.databinding.FragmentNewsBinding
import com.tunahanozatac.appcentapp.ui.adapter.NewsAdapter
import com.tunahanozatac.appcentapp.ui.viewmodel.NewsViewModel

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (binding.searchText.text.isEmpty()) {
            binding.searchButton2.visibility = View.GONE
        }
        binding.searchButton2.setOnClickListener {
            binding.searchText.setText("")
        }

        binding.searchButton2.setOnClickListener {
            val action = NewsFragmentDirections.actionNavigationHomeToNewsDetailsFragment()
            Navigation.findNavController(it).navigate(action)
        }
        initRecyc()
        initSearchBox()
        subScbribe()
    }

    fun initSearchBox() {
        binding.searchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    loadData("Türkiye")
                    binding.searchButton2.visibility = View.GONE
                } else {
                    binding.searchButton2.visibility = View.VISIBLE
                    loadData(s.toString())
                }


            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun subScbribe() {
        viewModel.getListObserver().observe(viewLifecycleOwner, {
            if (it != null) {
                newsAdapter.updateList(it.articles as ArrayList<Articles>)
                newsAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun loadData(q: String) {
        viewModel.makeApiCall(q)
    }

    fun initRecyc() {
        binding.newsRecycler.apply {
            newsAdapter = NewsAdapter()
            adapter = newsAdapter
        }
    }
}