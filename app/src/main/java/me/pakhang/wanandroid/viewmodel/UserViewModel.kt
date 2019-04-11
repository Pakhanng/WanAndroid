package me.pakhang.wanandroid.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import me.pakhang.wanandroid.repository.UserRepository

class UserViewModel(private val repo: UserRepository) : ViewModel() {

    val userName = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()

    fun signIn() {
        repo.signIn(userName.value, password.value)
    }

}
