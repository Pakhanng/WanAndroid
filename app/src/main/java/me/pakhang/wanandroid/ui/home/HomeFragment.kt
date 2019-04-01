package me.pakhang.wanandroid.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import me.pakhang.wanandroid.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val adapter = HomeAdapter()
        binding.articleList.adapter = adapter
        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: HomeAdapter, binding: FragmentHomeBinding) {
        val viewModel = ViewModelProviders.of(this, HomeViewModelFactory()).get(HomeViewModel::class.java)
        viewModel.banner.observe(this, Observer {
            Log.d("cbh", "observer, banner = $it")
            adapter.setBanner(it)
        })
        viewModel.articles.observe(this, Observer {
            Log.d("cbh", "observer, articles = $it")
            adapter.submitList(it)
        })
    }

}
