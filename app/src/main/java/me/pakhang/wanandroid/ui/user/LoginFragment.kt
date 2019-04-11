package me.pakhang.wanandroid.ui.user

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.transition.Slide
import androidx.transition.TransitionManager
import me.pakhang.wanandroid.databinding.FragmentLoginBinding
import me.pakhang.wanandroid.viewmodel.UserViewModel
import me.pakhang.wanandroid.viewmodel.UserViewModelFactory

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentLoginBinding.inflate(LayoutInflater.from(context), container, false)
            .apply {
                viewLifecycleOwner
                subscribeUi(this)
                return root
            }
    }

    private fun subscribeUi(binding: FragmentLoginBinding) {
        val viewModel =
            ViewModelProviders.of(this, UserViewModelFactory()).get(UserViewModel::class.java)
                .apply {
                    binding.viewModel = this
                    userName.observe(viewLifecycleOwner, Observer {
                        Log.d("cbh", "username = ${userName.value}")
                    })
                    password.observe(viewLifecycleOwner, Observer {
                        Log.d("cbh", "password = ${password.value}")
                    })
                    confirmPassword.observe(viewLifecycleOwner, Observer {
                        Log.d("cbh", "confirmPassword = ${confirmPassword.value}")
                    })
                }

        binding.apply {
            gotoSignIn = View.OnClickListener {
                TransitionManager.beginDelayedTransition(sceneRoot, Slide(Gravity.END))
                sceneSignIn.visibility = View.VISIBLE
                sceneSignUp.visibility = View.GONE
            }
            gotoSignUp = View.OnClickListener {
                TransitionManager.beginDelayedTransition(sceneRoot, Slide(Gravity.END))
                sceneSignIn.visibility = View.GONE
                sceneSignUp.visibility = View.VISIBLE
            }
            signIn = View.OnClickListener {
                Toast.makeText(context, "登录", Toast.LENGTH_SHORT).apply {
                    setText("登录")
                    show()
                }
                Log.d("cbh", "username = ${viewModel.userName.value}")
                Log.d("cbh", "password = ${viewModel.password.value}")
                viewModel.signIn()
            }
            signUp = View.OnClickListener {
                Toast.makeText(context, "注册", Toast.LENGTH_SHORT).apply {
                    setText("注册")
                    show()
                }
            }
        }
    }

}
