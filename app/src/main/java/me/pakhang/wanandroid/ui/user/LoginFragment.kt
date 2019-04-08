package me.pakhang.wanandroid.ui.user

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import me.pakhang.wanandroid.databinding.FragmentLoginBinding
import me.pakhang.wanandroid.model.UserPost

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var mUser: UserPost

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mUser = UserPost("", "")
        FragmentLoginBinding.inflate(LayoutInflater.from(context), container, false)
            .apply {
                inputUsername.addTextChangedListener {
                    mUser.username = it.toString()
                    user = mUser
                }
                inputPassword.addTextChangedListener {
                    mUser.password = it.toString()
                    user = mUser
                }
                login = View.OnClickListener {
                    Toast.makeText(context, "登录", Toast.LENGTH_SHORT).apply {
                        setText("登录")
                        show()
                    }
                }
                register = View.OnClickListener {

                }
                return root
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        // TODO: Use the ViewModel
    }

//    private fun subscribeUi(binding: FragmentProjectBinding) {
//        mViewModel.projectCategory.observe(this, Observer {
//            Log.d("cbh", "observer, projectCategory = $it")
//            binding.viewPager.adapter = ViewPagerAdapter(it)
//            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//                tab.text = Html.fromHtml(it[position].name)
//            }.attach()
//            binding.progressBar.hide()
//        })
//    }

}
