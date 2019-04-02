package me.pakhang.wanandroid.model

data class Project(
    val id: Int, val title: String, val author: String, val niceDate: String,
    val superChapterName: String, val chapterName: String, val link: String, val fresh: Boolean,
    val envelopePic: String, val desc: String
)
