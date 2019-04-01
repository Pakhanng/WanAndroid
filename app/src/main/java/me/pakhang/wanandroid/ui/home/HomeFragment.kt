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

    private lateinit var viewModel: HomeViewModel
    private lateinit var mAdapter: HomeAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        mAdapter = HomeAdapter()
        binding.articleList.adapter = mAdapter

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, HomeViewModelFactory()).get(HomeViewModel::class.java)
        viewModel.banner.observe(this, Observer {
            Log.d("cbh", "observer, banner = $it")
            mAdapter.setBanner(it)
        })
        viewModel.articles.observe(this, Observer {
            Log.d("cbh", "observer, articles = $it")
            mAdapter.submitList(it)
        })
    }


}
