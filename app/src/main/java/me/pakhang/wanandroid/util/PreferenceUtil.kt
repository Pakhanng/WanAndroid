package me.pakhang.wanandroid.util

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import me.pakhang.wanandroid.App
import me.pakhang.wanandroid.model.User

fun saveCookie(cookies: MutableList<String>) {
    PreferenceManager
        .getDefaultSharedPreferences(App.CONTEXT)
        .edit()
        .putStringSet("cookies", cookies.toSet())
        .apply()
}

fun saveUser(user: User) {
    App.CONTEXT.getSharedPreferences("user", Context.MODE_PRIVATE).edit()
        .putInt("id", user.id)
        .putString("name", user.username)
        .apply()
}

fun getCachedUser(): User? {
    val preferences = App.CONTEXT.getSharedPreferences("user", Context.MODE_PRIVATE)
    if (!preferences.contains("id")) return null
    return User(preferences.getInt("id", -1), preferences.getString("name", "")!!)
}

fun clearCachedUser() {
    val preferences = App.CONTEXT.getSharedPreferences("user", Context.MODE_PRIVATE)
    preferences.edit().clear().apply()
}

private fun hasCookie(): Boolean {
    val set = PreferenceManager.getDefaultSharedPreferences(App.CONTEXT).getStringSet(
        "cookies", null
    )
    Log.d("cbh", "set = $set")

    var loginUserName: String? = null
    for (cookie in set) {
        if (cookie.startsWith("loginUserName")) {
            loginUserName = cookie
        }
    }
    return loginUserName != null
}