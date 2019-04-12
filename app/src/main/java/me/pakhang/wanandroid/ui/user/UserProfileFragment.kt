package me.pakhang.wanandroid.ui.user

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.transition.Slide
import me.pakhang.wanandroid.R
import me.pakhang.wanandroid.databinding.FragmentUserProfileBinding
import me.pakhang.wanandroid.viewmodel.UserViewModel
import me.pakhang.wanandroid.viewmodel.UserViewModelFactory

class UserProfileFragment : Fragment() {

    private val mSlide by lazy { Slide(Gravity.END) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentUserProfileBinding.inflate(LayoutInflater.from(context), container, false)
            .apply {
                subscribeUi(this)
                return root
            }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val set = PreferenceManager.getDefaultSharedPreferences(context).getStringSet(
            "cookies", null
        )
        Log.d("cbh", "set = $set")

        var loginUserName: String? = null
        for (cookie in set) {
            if (cookie.startsWith("loginUserName")) {
                loginUserName = cookie
            }
        }
        if (loginUserName == null) {
            Log.d("cbh", "go login")
            findNavController().navigate(R.id.login_fragment)
        }
    }

    private fun subscribeUi(binding: FragmentUserProfileBinding) {
        ViewModelProviders.of(this, UserViewModelFactory()).get(UserViewModel::class.java)
            .apply {
                authenticationState.observe(viewLifecycleOwner, Observer {
                    if (it == UserViewModel.AuthenticationState.UNAUTHENTICATED)
                        findNavController().navigate(R.id.login_fragment)
                })
            }

        binding.apply {

        }
    }

}
