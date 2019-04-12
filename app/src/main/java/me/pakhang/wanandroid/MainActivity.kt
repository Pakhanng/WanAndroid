package me.pakhang.wanandroid

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import me.pakhang.wanandroid.databinding.ActivityMainBinding
import me.pakhang.wanandroid.databinding.DrawerHeaderBinding
import me.pakhang.wanandroid.ui.home.HomeFragmentDirections

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
        val drawerHeaderBinding: DrawerHeaderBinding =
            DataBindingUtil.bind(binding.navigationView.getHeaderView(0))!!
        drawerHeaderBinding.clickListener = View.OnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToUserProfileFragment()
            mNavController.navigate(direction)
            mDrawerLayout.closeDrawers()
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
                R.id.home_fragment, R.id.project_fragment, R.id.knowledge_fragment, R.id.navi_fragment -> View.VISIBLE
                else -> View.GONE
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
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
