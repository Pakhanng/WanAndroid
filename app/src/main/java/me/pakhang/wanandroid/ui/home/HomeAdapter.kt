package me.pakhang.wanandroid.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.pakhang.wanandroid.R
import me.pakhang.wanandroid.databinding.ListArticleBinding
import me.pakhang.wanandroid.databinding.ListBannerBinding

class HomeAdapter : PagedListAdapter<Article, RecyclerView.ViewHolder>(ArticleDiffCallback()) {

    private lateinit var mBannerItems: List<BannerItem>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.list_article ->
                ArticleViewHolder(ListArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            R.layout.list_banner ->
                BannerViewHolder(ListBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("cbh", "onBindViewHolder, position= $position")
        when (getItemViewType(position)) {
            R.layout.list_article -> (holder as ArticleViewHolder).bind(getItem(position - 1)!!)
            R.layout.list_banner -> (holder as BannerViewHolder).bind(mBannerItems)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            R.layout.list_banner
        } else {
            R.layout.list_article
        }
    }

    fun setBanner(list: List<BannerItem>) {
        mBannerItems = list
        notifyItemChanged(0)
    }

    class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            Log.d("cbh", "areItemsTheSame: oldItem = $oldItem, newItem = $newItem")
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            Log.d("cbh", "areContentsTheSame: oldItem = $oldItem, newItem = $newItem")
            return oldItem == newItem
        }

    }
}
