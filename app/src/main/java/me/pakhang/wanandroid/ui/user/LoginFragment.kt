package me.pakhang.wanandroid.ui.user

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.transition.Slide
import androidx.transition.TransitionManager
import me.pakhang.wanandroid.R
import me.pakhang.wanandroid.databinding.FragmentLoginBinding
import me.pakhang.wanandroid.model.Status
import me.pakhang.wanandroid.viewmodel.UserViewModel
import me.pakhang.wanandroid.viewmodel.UserViewModelFactory

class LoginFragment : Fragment() {

    private val mSlide by lazy { Slide(Gravity.END) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        FragmentLoginBinding.inflate(LayoutInflater.from(context), container, false)
            .apply {
                subscribeUi(this)
                return root
            }
    }

    private fun subscribeUi(binding: FragmentLoginBinding) {
        val viewModel =
            ViewModelProviders.of(this, UserViewModelFactory()).get(UserViewModel::class.java)
                .apply {
                    binding.viewModel = this
                }

        binding.apply {
            gotoSignIn = View.OnClickListener {
                TransitionManager.beginDelayedTransition(sceneRoot, mSlide)
                sceneSignIn.visibility = View.VISIBLE
                sceneSignUp.visibility = View.GONE
            }
            gotoSignUp = View.OnClickListener {
                TransitionManager.beginDelayedTransition(sceneRoot, mSlide)
                sceneSignIn.visibility = View.GONE
                sceneSignUp.visibility = View.VISIBLE
            }
            signIn = View.OnClickListener {
                val resource = viewModel.signIn()
                resource.observe(viewLifecycleOwner, Observer {
                    when(resource.value!!.status) {
                        Status.SUCCESS -> Log.d("cbh", "user = ${resource.value!!.data}")
                    }

                })
            }
            signUp = View.OnClickListener {
                viewModel.signUp()
            }
        }

        requireActivity().addOnBackPressedCallback(viewLifecycleOwner, OnBackPressedCallback {
//            viewModel.refuseAuthentication()
            findNavController().popBackStack(R.id.home_fragment, false)
            true
        })

    }



}
