package me.pakhang.wanandroid.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.pakhang.wanandroid.R
import me.pakhang.wanandroid.databinding.ItemListArticleBinding
import me.pakhang.wanandroid.databinding.ItemListBannerBinding
import me.pakhang.wanandroid.model.Article
import me.pakhang.wanandroid.model.BannerItem

class HomeAdapter : PagedListAdapter<Article, RecyclerView.ViewHolder>(ArticleDiffCallback()) {

    private var mBannerItems = listOf<BannerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_list_article ->
                ArticleViewHolder(
                    ItemListArticleBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            R.layout.item_list_banner ->
                BannerViewHolder(
                    ItemListBannerBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            else -> throw IllegalArgumentException("unknown view type $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("cbh", "onBindViewHolder, position= $position")
        when (getItemViewType(position)) {
            R.layout.item_list_article -> (holder as ArticleViewHolder).bind(getItem(position - 1)!!)
            R.layout.item_list_banner -> (holder as BannerViewHolder).bind(mBannerItems)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            R.layout.item_list_banner
        } else {
            R.layout.item_list_article
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
