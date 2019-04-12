package me.pakhang.wanandroid.viewmodel

import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import me.pakhang.wanandroid.R
import me.pakhang.wanandroid.repository.UserRepository

class UserViewModel(private val repo: UserRepository) : ViewModel() {

    enum class AuthenticationState {
        AUTHENTICATED,          // Initial state, the user needs to authenticate
        UNAUTHENTICATED,        // The user has authenticated successfully
        INVALID_AUTHENTICATION  // Authentication failed
    }



    val userName = MutableLiveData<String>()

    val password = MutableLiveData<String>()
    val confirmPassword = MutableLiveData<String>()

    val authenticationState: MutableLiveData<AuthenticationState> = Transformations.switchMap(userName, password) {
        repo.signIn(userName.value!!, password.value!!)
    }

    init {
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
    }

//    fun checkCookie() {
//        val set = PreferenceManager.getDefaultSharedPreferences(context).getStringSet(
//            "cookies", null
//        )
//        Log.d("cbh", "set = $set")
//
//        var loginUserName: String? = null
//        for (cookie in set) {
//            if (cookie.startsWith("loginUserName")) {
//                loginUserName = cookie
//            }
//        }
//        if (loginUserName == null) {
//            Log.d("cbh", "go login")
//        }
//    }

//    fun signIn() = repo.signIn(userName.value!!, password.value!!)

    fun signUp() {
        repo.signUp(userName.value!!, password.value!!, confirmPassword.value!!)
    }

}
