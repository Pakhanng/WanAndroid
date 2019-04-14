package me.pakhang.wanandroid.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.pakhang.wanandroid.App
import me.pakhang.wanandroid.model.Resource
import me.pakhang.wanandroid.model.User
import me.pakhang.wanandroid.repository.UserRepository

class UserViewModel(private val repo: UserRepository) : ViewModel() {

//    enum class AuthenticationState {
//        AUTHENTICATED,          // Initial state, the user needs to authenticate
//        UNAUTHENTICATED,        // The user has authenticated successfully
//        INVALID_AUTHENTICATION  // Authentication failed
//    }

//    val authenticationState = MutableLiveData<AuthenticationState>()

    val user = MutableLiveData<User>(App.getUser())

    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()

    fun signIn() = repo.signIn(userName.value!!, password.value!!)

    fun signUp() = repo.signUp(userName.value!!, password.value!!, confirmPassword.value!!)

    fun setUser(u: User) {
        user.value = u
    }

    fun signOut() {
        repo.signOut()
        user.value = null
        Log.d("cbh", "UserViewModel signOut, set user.value = ${user.value}")

    }

}
