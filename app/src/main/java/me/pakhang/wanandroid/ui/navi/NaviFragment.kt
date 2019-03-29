package me.pakhang.wanandroid.ui.navi

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import me.pakhang.wanandroid.R

class NaviFragment : Fragment() {

    companion object {
        fun newInstance() = NaviFragment()
    }

    private lateinit var viewModel: NaviViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_navi, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NaviViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
