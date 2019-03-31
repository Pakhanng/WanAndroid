package me.pakhang.wanandroid.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.pakhang.wanandroid.databinding.ListArticleBinding


class ArticleAdapter : PagedListAdapter<Article, ArticleAdapter.ViewHolder>(ArticleDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ListArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class ViewHolder(private val binding: ListArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
            binding.clickListener = View.OnClickListener {
                val direction =
                    HomeFragmentDirections.actionHomeFragmentToArticleDetailFragment(article.link)
                it.findNavController().navigate(direction)
            }
        }
    }
}

private class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        Log.d("cbh", "areItemsTheSame: oldItem = $oldItem, newItem = $newItem")
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        Log.d("cbh", "areContentsTheSame: oldItem = $oldItem, newItem = $newItem")
        return oldItem == newItem
    }

}
