package me.pakhang.wanandroid.ui.project

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import me.pakhang.wanandroid.databinding.ItemListProjectBinding
import me.pakhang.wanandroid.model.Project

class ProjectAdapter : PagedListAdapter<Project, ProjectViewHolder>(ProjectDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        return ProjectViewHolder(ItemListProjectBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        Log.d("cbh", "onBindViewHolder, position= $position")
        holder.bind(getItem(position)!!)
    }

    class ProjectDiffCallback : DiffUtil.ItemCallback<Project>() {
        override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
            Log.d("cbh", "areItemsTheSame: oldItem = $oldItem, newItem = $newItem")
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
            Log.d("cbh", "areContentsTheSame: oldItem = $oldItem, newItem = $newItem")
            return oldItem == newItem
        }
    }
}
