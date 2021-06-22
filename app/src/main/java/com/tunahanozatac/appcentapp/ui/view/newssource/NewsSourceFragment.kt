package com.tunahanozatac.appcentapp.ui.view.newssource

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.tunahanozatac.appcentapp.R
import com.tunahanozatac.appcentapp.databinding.FragmentNewsSourceBinding

class NewsSourceFragment : Fragment() {

    private lateinit var dataBinding: FragmentNewsSourceBinding
    private val args: NewsSourceFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_source, container, false)
        return inflater.inflate(R.layout.fragment_news_source, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            webViewSetup()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    fun webViewSetup() {
        dataBinding.webView.webViewClient = WebViewClient()
        dataBinding.webView.apply {
            loadUrl(args.url)
            settings.javaScriptEnabled = true
            settings.safeBrowsingEnabled = true
        }
    }
}