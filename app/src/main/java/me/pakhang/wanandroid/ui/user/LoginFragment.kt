package me.pakhang.wanandroid.ui.user

import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.transition.Slide
import androidx.transition.TransitionManager
import me.pakhang.wanandroid.MainActivity
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
            ViewModelProviders.of(activity!!, UserViewModelFactory()).get(UserViewModel::class.java)
                .apply {
                    binding.viewModel = this
                }

        binding.apply {
            progressBar.hide()

            usernameInput.addTextChangedListener { binding.viewModel = viewModel }
            passwordInput.addTextChangedListener { binding.viewModel = viewModel }
            confirmPasswordInput.addTextChangedListener { binding.viewModel = viewModel }

            gotoSignIn = View.OnClickListener {
                TransitionManager.beginDelayedTransition(sceneRoot, mSlide)
                sceneSignIn.visibility = View.VISIBLE
                sceneSignUp.visibility = View.GONE
            }

            gotoSignUp = View.OnClickListener {
                TransitionManager.beginDelayedTransition(sceneRoot, mSlide)
                sceneSignIn.visibility = View.GONE
                sceneSignUp.visibility = View.VISIBLE
                passwordInputLayout.error = null
            }

            signIn = View.OnClickListener {
                passwordInputLayout.error = null
                val resource = viewModel.signIn()
                resource.observe(viewLifecycleOwner, Observer {
                    val res = resource.value!!
                    when (res.status) {
                        Status.SUCCESS -> {
                            viewModel.setUser(res.data!!)
                            findNavController().popBackStack()
                            progressBar.hide()
                        }
                        Status.ERROR -> {
                            passwordInputLayout.error = res.message
                            progressBar.hide()
                        }
                        Status.LOADING -> {
                            progressBar.show()
                        }
                    }

                })
            }

            signUp = View.OnClickListener {
                confirmPasswordInputLayout.error = null
                val resource = viewModel.signUp()
                resource.observe(viewLifecycleOwner, Observer {
                    val res = resource.value!!
                    when (res.status) {
                        Status.SUCCESS -> {
                            viewModel.setUser(res.data!!)
                            findNavController().popBackStack()
                            progressBar.hide()
                        }
                        Status.ERROR -> {
                            confirmPasswordInputLayout.error = res.message
                            progressBar.hide()
                        }
                        Status.LOADING -> {
                            progressBar.show()
                        }
                    }
                })
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback {
            findNavController().popBackStack(R.id.home_fragment, false)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("cbh", "$item")
        return super.onOptionsItemSelected(item)
    }

}
