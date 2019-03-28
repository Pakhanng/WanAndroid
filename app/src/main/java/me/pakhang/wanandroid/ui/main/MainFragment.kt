package me.pakhang.wanandroid.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.main_fragment.*
import me.pakhang.wanandroid.R
import me.pakhang.wanandroid.ui.home.HomeFragment
import me.pakhang.wanandroid.ui.navi.NaviFragment
import me.pakhang.wanandroid.ui.project.ProjectFragment
import me.pakhang.wanandroid.ui.tree.TreeFragment

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment.newInstance())
                    .commitNow()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_project -> {
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, ProjectFragment.newInstance())
                    .commitNow()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tree -> {
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, TreeFragment.newInstance())
                    .commitNow()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_navi -> {
                activity!!.supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, NaviFragment.newInstance())
                    .commitNow()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
//        ↑ => return@OnNavigationItemSelectedListener false
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
