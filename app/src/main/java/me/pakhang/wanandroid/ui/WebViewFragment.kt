package me.pakhang.wanandroid.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_web_view.*
import me.pakhang.wanandroid.R

class WebViewFragment : Fragment() {

    private val args: WebViewFragmentArgs by navArgs()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        web_view.settings.javaScriptEnabled = true
        web_view.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                if (activity != null)
                    (activity as AppCompatActivity).supportActionBar!!.title = title
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                if (progress_bar == null) {
                    return
                }
                if (newProgress == 100) progress_bar.visibility = View.GONE
                progress_bar.progress = newProgress
            }
        }
        web_view.webViewClient = WebViewClient()
//        web_view.loadUrl(arguments.getString("link"))
        web_view.loadUrl(args.link)
    }
}
