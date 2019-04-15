package me.pakhang.wanandroid.ui.knowledge

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import me.pakhang.wanandroid.databinding.FragmentKnowledgeArticleBinding
import me.pakhang.wanandroid.viewmodel.KnowledgeViewModel
import me.pakhang.wanandroid.viewmodel.KnowledgeViewModelFactory

class KnowledgeArticleFragment : Fragment() {
    private val args: KnowledgeArticleFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentKnowledgeArticleBinding.inflate(LayoutInflater.from(context), container, false)
            .apply {
                val adapter = KnowledgeArticleAdapter()
                articleList.adapter = adapter
                subscribeUi(adapter, this)
                return root
            }
    }

    private fun subscribeUi(adapter: KnowledgeArticleAdapter, binding: FragmentKnowledgeArticleBinding) {
        ViewModelProviders
            .of(this, KnowledgeViewModelFactory())
            .get(KnowledgeViewModel::class.java)
            .apply {
                getArticles(args.cid).observe(viewLifecycleOwner, Observer {
                    Log.d("cbh", "observer, articles = $it")
                    adapter.submitList(it)
                    binding.progressBar.hide()
                })
            }
    }


}
