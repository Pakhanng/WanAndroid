package me.pakhang.wanandroid

import android.app.Application
import android.content.Context
import me.pakhang.wanandroid.model.User
import me.pakhang.wanandroid.util.clearCachedUser
import me.pakhang.wanandroid.util.getCachedUser
import me.pakhang.wanandroid.util.saveUser


class App : Application() {

    companion object {
        lateinit var CONTEXT: Context

        private var mUser: User? = null

        fun getUser() = mUser

        fun isLogin() = mUser != null

        fun signIn(user: User) {
            mUser = user
            saveUser(user)
        }

        fun signOut() {
            mUser = null
            clearCachedUser()
        }
    }

    override fun onCreate() {
        super.onCreate()
        CONTEXT = this
        mUser = getCachedUser()
    }
}