package com.tunahanozatac.appcentapp.ui.view.newssource

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.tunahanozatac.appcentapp.R
import com.tunahanozatac.appcentapp.databinding.FragmentNewsSourceBinding

class NewsSourceFragment : Fragment() {

    private lateinit var dataBinding: FragmentNewsSourceBinding
    private val args: NewsSourceFragmentArgs by navArgs()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_source, container, false)

        dataBinding.webView.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = WebViewClient()
                loadUrl(args.url)
        }
        return dataBinding.root
    }
}