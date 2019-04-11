package me.pakhang.wanandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.pakhang.wanandroid.repository.UserRepository


@Suppress("UNCHECKED_CAST")
class UserViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>) = UserViewModel(UserRepository) as T
}