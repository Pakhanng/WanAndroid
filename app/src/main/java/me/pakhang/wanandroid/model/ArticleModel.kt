package me.pakhang.wanandroid.model

import me.pakhang.wanandroid.ui.home.Article

class ArticleModel {
    var data: Data? = null
    var errorCode: Int = 0
    var errorMsg: String? = null

    inner class Data {
        var datas: List<Article>? = null
    }
}
