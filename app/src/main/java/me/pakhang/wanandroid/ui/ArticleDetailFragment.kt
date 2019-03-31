package me.pakhang.wanandroid.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_article_detail.*
import me.pakhang.wanandroid.R


class ArticleDetailFragment : Fragment() {

    private val args: ArticleDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article_detail, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Log.d("cbh", "activity.actionBar=${activity!!.actionBar}")

        web_view.settings.javaScriptEnabled = true
        web_view.webChromeClient = object: WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                (activity as AppCompatActivity).supportActionBar!!.title = title
            }
        }
        web_view.webViewClient = WebViewClient()
//        web_view.loadUrl(arguments.getString("link"))
        web_view.loadUrl(args.link)
    }
}
