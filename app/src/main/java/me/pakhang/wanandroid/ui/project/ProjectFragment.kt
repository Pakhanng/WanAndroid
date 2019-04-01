package me.pakhang.wanandroid.ui.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import me.pakhang.wanandroid.databinding.FragmentProjectBinding

class ProjectFragment : Fragment() {

    private lateinit var viewModel: ProjectViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentProjectBinding.inflate(layoutInflater, container, false)
        subscribeUi(binding)
        return binding.root
    }


    private fun subscribeUi(binding: FragmentProjectBinding) {
        val viewModel = ViewModelProviders.of(this).get(ProjectViewModel::class.java)
//        viewModel.articles.observe(this, Observer {
//            Log.d("cbh", "observer, articles = $it")
//            adapter.submitList(it)
//        })
    }




}
