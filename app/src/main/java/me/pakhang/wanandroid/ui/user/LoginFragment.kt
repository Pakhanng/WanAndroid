package me.pakhang.wanandroid.ui.user

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.transition.Slide
import androidx.transition.TransitionManager
import me.pakhang.wanandroid.databinding.FragmentLoginBinding
import me.pakhang.wanandroid.model.UserPost
import me.pakhang.wanandroid.viewmodel.UserViewModel
import me.pakhang.wanandroid.viewmodel.UserViewModelFactory

class LoginFragment : Fragment() {

    private lateinit var mUser: UserPost

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mUser = UserPost("", "", "")
        FragmentLoginBinding.inflate(LayoutInflater.from(context), container, false)
            .apply {
                subscribeUi(this)
                return root
            }
    }

    private fun subscribeUi(binding: FragmentLoginBinding) {
//        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        ViewModelProviders.of(this, UserViewModelFactory()).get(UserViewModel::class.java)

        binding.apply {
            inputUsername.addTextChangedListener {
                mUser.username = it.toString()
                user = mUser
            }
            inputPassword.addTextChangedListener {
                mUser.password = it.toString()
                user = mUser
            }
            inputConfirmPassword.addTextChangedListener {
                mUser.confirmPassword = it.toString()
                user = mUser
            }
            gotoLogin = View.OnClickListener {
                TransitionManager.beginDelayedTransition(sceneRoot, Slide(Gravity.END))
                sceneLogin.visibility = View.VISIBLE
                sceneRegister.visibility = View.GONE
            }
            gotoRegister = View.OnClickListener {
                TransitionManager.beginDelayedTransition(sceneRoot, Slide(Gravity.END))
                sceneLogin.visibility = View.GONE
                sceneRegister.visibility = View.VISIBLE
            }
            login = View.OnClickListener {
                Toast.makeText(context, "登录", Toast.LENGTH_SHORT).apply {
                    setText("登录")
                    show()
                }
            }
            register = View.OnClickListener {
                Toast.makeText(context, "注册", Toast.LENGTH_SHORT).apply {
                    setText("注册")
                    show()
                }
            }
        }
    }

}
