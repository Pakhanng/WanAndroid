package me.pakhang.wanandroid.ui.project

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import me.pakhang.wanandroid.databinding.ItemListProjectBinding
import me.pakhang.wanandroid.model.Project

class ProjectViewHolder(private val binding: ItemListProjectBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project) {
            binding.project = project
            binding.clickListener = View.OnClickListener {
                val direction = ProjectFragmentDirections.actionProjectFragmentToArticleDetailFragment(project.link)
                it.findNavController().navigate(direction)
            }
        }
}
