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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tunahanozatac.appcentapp.R
import com.tunahanozatac.appcentapp.databinding.FragmentNewsBinding
import com.tunahanozatac.appcentapp.ui.adapter.NewsAdapter
import com.tunahanozatac.appcentapp.ui.viewmodel.NewsViewModel
import kotlinx.coroutines.*

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
    private var searchJob: Job? = null
    var page = 1

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

        initRecyclerView()
        initSearchBox()
        subSubscribe()
    }

    private fun initSearchBox() {
        binding.searchText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                page = 1
                if (s.isNullOrEmpty()) {
                    viewModel.searchValue.value = "Turkiye"
                    binding.searchButton2.visibility = View.GONE
                } else {
                    searchJob?.cancel()
                    searchJob = coroutineScope.launch {
                        s.let {
                            delay(1000)
                            binding.searchButton2.visibility = View.VISIBLE
                            viewModel.searchValue.value = s.toString()
                        }
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    private fun subSubscribe() {
        viewModel.getListObserver().observe(viewLifecycleOwner, {
            if (it != null) {
                binding.newsIsLoading.visibility = View.GONE
                newsAdapter.updateList(it.articles, page)
            }
        })

        viewModel.searchValue.observe(viewLifecycleOwner, {
            it?.let {
                loadData(it)
            }
        })
    }

    private fun loadData(q: String) {
        viewModel.makeApiCall(q, page)
    }

    private fun initRecyclerView() {
        layoutManager = LinearLayoutManager(context)
        binding.newsRecycler.layoutManager = layoutManager
        binding.newsRecycler.apply {
            newsAdapter = NewsAdapter()
            adapter = newsAdapter
        }

        binding.newsRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0 && !recyclerView.canScrollVertically(2)) {
                    page++
                    loadData(viewModel.searchValue.value.toString())
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
}