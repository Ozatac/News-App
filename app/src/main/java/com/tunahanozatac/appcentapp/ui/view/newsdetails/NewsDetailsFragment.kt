package com.tunahanozatac.appcentapp.ui.view.newsdetails

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.tunahanozatac.appcentapp.R
import com.tunahanozatac.appcentapp.databinding.FragmentNewsDetailsBinding

class NewsDetailsFragment : Fragment() {

    private lateinit var dataBinding: FragmentNewsDetailsBinding
    private val args: NewsDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_news_details, container, false)

        dataBinding.news = args.model
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.shareBtn.setOnClickListener {
            try {
                val i = Intent(Intent.ACTION_SEND)
                i.type = "text/plan"
                i.putExtra(Intent.EXTRA_SUBJECT, args.model.title)
                val body: String =
                    args.model.title + "\n" + args.model.url + "\n" + "Haber Uygulamasından Paylaşın" + "\n"
                i.putExtra(Intent.EXTRA_TEXT, body)
                startActivity(Intent.createChooser(i, "İle paylaş :"))
            } catch (e: Exception) {
                Toast.makeText(context, "Afedersiniz,\n paylaşılamaz", Toast.LENGTH_LONG).show()
            }
        }

        dataBinding.openWebSite.setOnClickListener {
            val action = args.model.url.let { url ->
                NewsDetailsFragmentDirections.actionNewsDetailsFragmentToNewsSourceFragment(url)
            }
            action.let { action -> findNavController().navigate(action) }
        }
    }
}