package com.tunahanozatac.appcentapp.ui.view.News

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tunahanozatac.appcentapp.R
import com.tunahanozatac.appcentapp.data.model.Articles
import com.tunahanozatac.appcentapp.data.model.NewsResponse
import com.tunahanozatac.appcentapp.databinding.FragmentNewsBinding
import com.tunahanozatac.appcentapp.ui.adapter.NewsAdapter
import com.tunahanozatac.appcentapp.ui.view.NewsDetails.NewsDetailsFragmentArgs
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
        initRecyc()
        initSearchBox()
        subScbribe()
    }

    fun initSearchBox() {
        binding.searchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                loadData(s.toString())
            }
        }
        )
    }

    private fun subScbribe(){
        viewModel.getListObserver().observe(viewLifecycleOwner, Observer<NewsResponse> {
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