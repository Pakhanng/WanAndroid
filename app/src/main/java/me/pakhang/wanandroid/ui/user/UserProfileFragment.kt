package me.pakhang.wanandroid.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import me.pakhang.wanandroid.App
import me.pakhang.wanandroid.R
import me.pakhang.wanandroid.databinding.FragmentUserProfileBinding
import me.pakhang.wanandroid.viewmodel.UserViewModel
import me.pakhang.wanandroid.viewmodel.UserViewModelFactory

class UserProfileFragment : Fragment() {

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

    private fun subscribeUi(binding: FragmentUserProfileBinding) {
        Log.d("cbh","isLogin ${App.isLogin()}")
        val viewModel = ViewModelProviders.of(activity!!, UserViewModelFactory()).get(UserViewModel::class.java)
            .apply {
                binding.user = user.value
                user.observe(viewLifecycleOwner, Observer {
                    Log.d("cbh", "UserProfileFragment observe user = ${user.value}")
                    if (it == null)
                        findNavController().navigate(R.id.login)

                })
            }
        binding.apply {
            signOut = View.OnClickListener {
                viewModel.signOut()
                findNavController().popBackStack(R.id.home, false)
            }
        }
    }

}
