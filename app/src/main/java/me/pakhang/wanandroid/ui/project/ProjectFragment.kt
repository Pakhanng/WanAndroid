package me.pakhang.wanandroid.ui.project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import me.pakhang.wanandroid.databinding.FragmentProjectBinding
import me.pakhang.wanandroid.databinding.ItemPageProjectsBinding
import me.pakhang.wanandroid.model.ProjectCategory
import me.pakhang.wanandroid.viewmodel.ProjectViewModel
import me.pakhang.wanandroid.viewmodel.ProjectViewModelFactory

class ProjectFragment : Fragment() {

    private lateinit var mViewModel: ProjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewModel = ViewModelProviders.of(this, ProjectViewModelFactory())
            .get(ProjectViewModel::class.java)
        val binding = FragmentProjectBinding.inflate(layoutInflater, container, false)
        binding.root.postDelayed({ subscribeUi(binding) }, 100)
        return binding.root
    }

    private fun subscribeUi(binding: FragmentProjectBinding) {
        mViewModel.projectCategory.observe(this, Observer {
            Log.d("cbh", "observer, projectCategory = $it")
            binding.viewPager.adapter = ViewPagerAdapter(it)
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                tab.text = it[position].name
            }.attach()
        })
    }

    inner class ViewPagerAdapter(private val categories: List<ProjectCategory>) :
        RecyclerView.Adapter<PageViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
            return PageViewHolder(
                ItemPageProjectsBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
            holder.bind(categories[position].id)
        }

        override fun getItemCount(): Int {
            return categories.size
        }
    }

    inner class PageViewHolder internal constructor(private val binding: ItemPageProjectsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(id: Int) {
            val adapter = ProjectAdapter()
            binding.projectPage.adapter = adapter
            mViewModel.getProjects(id).observe(viewLifecycleOwner, Observer {
                Log.d("cbh", "observer, projects = $it")
                adapter.submitList(it)
            })
        }
    }

}
