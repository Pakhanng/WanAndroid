package me.pakhang.wanandroid.model

import androidx.paging.PagedList
import me.pakhang.wanandroid.ui.home.Article

class ArticleModel {
    var data: Data? = null
    var errorCode: Int = 0
    var errorMsg: String? = null

    inner class Data {
        var datas: PagedList<Article>? = null
    }
}
