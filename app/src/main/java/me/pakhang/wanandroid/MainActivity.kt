package me.pakhang.wanandroid

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import me.pakhang.wanandroid.databinding.ActivityMainBinding
import me.pakhang.wanandroid.databinding.DrawerHeaderBinding
import me.pakhang.wanandroid.ui.home.HomeFragmentDirections
import me.pakhang.wanandroid.viewmodel.UserViewModel
import me.pakhang.wanandroid.viewmodel.UserViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavController: NavController
    private lateinit var mAppBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        mDrawerLayout = binding.drawerLayout

        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment) //初始化导航控制器
        mAppBarConfiguration = AppBarConfiguration(mNavController.graph, mDrawerLayout) //导航配置

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(mNavController, mAppBarConfiguration) //设置 ActionBar 的导航图标
//        setupActionBarWithNavController(mNavController, mDrawerLayout)

        binding.bottomNavigationView.setupWithNavController(mNavController) //将底部导航栏加入导航
        binding.navigationView.setupWithNavController(mNavController) //将侧滑菜单栏加入导航


        // 设置侧滑菜单头部点击事件
        val userViewModel = ViewModelProviders.of(this@MainActivity, UserViewModelFactory()).get(
            UserViewModel::class.java
        )
        val drawerHeaderBinding: DrawerHeaderBinding =
            DataBindingUtil.bind(binding.navigationView.getHeaderView(0))!!
        drawerHeaderBinding.apply {
            userViewModel.user.observe(this@MainActivity, Observer {
                Log.d("cbh", "MainActivity observe user = ${userViewModel.user.value}")
                user = userViewModel.user.value
            })
            clickListener = View.OnClickListener {
                val direction = HomeFragmentDirections.actionHomeToUserProfile()
                mNavController.navigate(direction)
                mDrawerLayout.closeDrawers()
            }
        }

        //解决点击重复创建Fragment的问题
        binding.bottomNavigationView.setOnNavigationItemReselectedListener {
            Log.d("cbh", it.toString())
            Log.d("cbh", "fragment = ${mNavController.currentDestination}")
        }
//        binding.bnav_host_fragment

        // 某些页面隐藏底部导航栏
        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bottomNavigationView.visibility = when (destination.id) {
                R.id.home, R.id.project, R.id.knowledge, R.id.navi -> View.VISIBLE
                else -> View.GONE
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        // 登录页面中点击左上返回键直接回到首页
        val currentDestination = mNavController.currentDestination
        if (currentDestination != null && currentDestination.id == R.id.login) {
            mNavController.popBackStack(R.id.home, false)
            return false
        }

        return mNavController.navigateUp(mAppBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
