package me.pakhang.wanandroid.model

data class Knowledge(val id: Int, val name: String, val children: List<KnowledgeChild>)

data class KnowledgeChild(val id: Int, val name: String)
