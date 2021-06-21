package com.tunahanozatac.appcentapp.ui.view.newsdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tunahanozatac.appcentapp.R
import com.tunahanozatac.appcentapp.databinding.ActivityMainBinding
import com.tunahanozatac.appcentapp.databinding.FragmentNewsDetailsBinding

class NewsDetailsFragment : Fragment() {

    private lateinit var dataBinding: FragmentNewsDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_details, container, false)
        return dataBinding.root
    }
}