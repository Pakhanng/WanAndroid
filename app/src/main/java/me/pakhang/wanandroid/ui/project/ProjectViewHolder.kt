package me.pakhang.wanandroid.ui.project

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import me.pakhang.wanandroid.databinding.ItemListProjectBinding
import me.pakhang.wanandroid.model.Project

class ProjectViewHolder(private val binding: ItemListProjectBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(project: Project) {
            binding.project = project
            binding.clickListener = View.OnClickListener {
                val direction = ProjectFragmentDirections.actionProjectFragmentToWebViewFragment(project.link)
                it.findNavController().navigate(direction)
            }
            Glide.with(binding.root).load(project.envelopePic).into(binding.envelopePic)
        }
}
