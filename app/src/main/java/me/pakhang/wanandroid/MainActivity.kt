package me.pakhang.wanandroid

import android.os.Bundle
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

class MainActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mNavController: NavController
    private lateinit var mAppBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mDrawerLayout = binding.drawerLayout

        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment) //初始化导航控制器
        mAppBarConfiguration = AppBarConfiguration(mNavController.graph, mDrawerLayout) //导航配置

        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(mNavController, mAppBarConfiguration) //设置 ActionBar 的导航图标
//        setupActionBarWithNavController(mNavController, mDrawerLayout)

        binding.bottomNavigationView.setupWithNavController(mNavController) //将底部导航栏加入导航
        binding.navigationView.setupWithNavController(mNavController) //将侧滑菜单栏加入导航

        // 侧滑菜单的页面，隐藏底部导航栏
        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_collect) {
                binding.bottomNavigationView.visibility = View.GONE
            } else {
                binding.bottomNavigationView.visibility = View.VISIBLE
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
