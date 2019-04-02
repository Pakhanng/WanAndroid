package me.pakhang.wanandroid.ui.collect

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import me.pakhang.wanandroid.R
import me.pakhang.wanandroid.viewmodel.CollectViewModel

class CollectFragment : Fragment() {

    companion object {
        fun newInstance() = CollectFragment()
    }

    private lateinit var viewModel: CollectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_collect, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CollectViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
