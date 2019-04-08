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
import me.pakhang.wanandroid.viewmodel.HomeViewModel
import me.pakhang.wanandroid.viewmodel.HomeViewModelFactory

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentHomeBinding.inflate(layoutInflater, container, false)
            .apply {
                val adapter = HomeAdapter()
                articleList.adapter = adapter
                subscribeUi(adapter, this)
                return root
            }
    }

    private fun subscribeUi(adapter: HomeAdapter, binding: FragmentHomeBinding) {
        ViewModelProviders.of(this, HomeViewModelFactory()).get(HomeViewModel::class.java)
            .apply {
                banner.observe(viewLifecycleOwner, Observer {
                    Log.d("cbh", "observer, banner = $it")
                    adapter.setBanner(it)
                })
                articles.observe(viewLifecycleOwner, Observer {
                    Log.d("cbh", "observer, articles = $it")
                    adapter.submitList(it)
                    binding.progressBar.hide()
                })
            }
    }

}
