package me.pakhang.wanandroid.ui.knowledge

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import me.pakhang.wanandroid.R

class KnowledgeFragment : Fragment() {

    companion object {
        fun newInstance() = KnowledgeFragment()
    }

    private lateinit var viewModel: KnowledgeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tree, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(KnowledgeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
