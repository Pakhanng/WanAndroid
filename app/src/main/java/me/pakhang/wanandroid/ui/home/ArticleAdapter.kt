package me.pakhang.wanandroid.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.pakhang.wanandroid.databinding.ListArticleBinding

class ArticleAdapter : ListAdapter<Article, ArticleAdapter.ViewHolder>(ArticleDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                ListArticleBinding.inflate(LayoutInflater.from(parent.context),
                        parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ListArticleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
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
