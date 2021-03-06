package me.pakhang.wanandroid.ui.knowledge

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import me.pakhang.wanandroid.App
import me.pakhang.wanandroid.R
import me.pakhang.wanandroid.databinding.FragmentKnowledgeBinding
import me.pakhang.wanandroid.viewmodel.KnowledgeViewModel
import me.pakhang.wanandroid.viewmodel.KnowledgeViewModelFactory

class KnowledgeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentKnowledgeBinding.inflate(LayoutInflater.from(context), container, false)
            .apply {
                val view = LayoutInflater.from(context)
                    .inflate(R.layout.list_header_footer_blank, container, false)
                knowledgeList.addHeaderView(view)
                knowledgeList.addFooterView(view)
                subscribeUi(this)
                return root
            }
    }

    private fun subscribeUi(binding: FragmentKnowledgeBinding) {
        ViewModelProviders
            .of(this, KnowledgeViewModelFactory())
            .get(KnowledgeViewModel::class.java)
            .apply {
                knowledge.observe(viewLifecycleOwner, Observer {
                    Log.d("cbh", "knowledge = $knowledge")
                    val adapter = KnowledgeListAdapter(knowledge.value!!)
                    binding.knowledgeList.setAdapter(adapter)
                    binding.progressBar.hide()
                })
            }
    }


}
