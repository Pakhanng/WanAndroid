package me.pakhang.wanandroid.ui.tree

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import me.pakhang.wanandroid.R

class TreeFragment : Fragment() {

    companion object {
        fun newInstance() = TreeFragment()
    }

    private lateinit var viewModel: TreeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.tree_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TreeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
