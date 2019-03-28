package me.pakhang.wanandroid

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {

    private lateinit var mNavController: NavController
    private lateinit var mAppBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment)
        mAppBarConfiguration = AppBarConfiguration(mNavController.graph, drawer_layout)

        setSupportActionBar(toolbar)
        navigation_view.setupWithNavController(mNavController) //将侧滑菜单栏加入导航
        bottom_navigation_view.setupWithNavController(mNavController) //将底部导航栏加入导航

        // 侧滑菜单的页面，隐藏底部导航栏
        mNavController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.navigation_collect) {
                bottom_navigation_view.visibility = View.GONE
            } else {
                bottom_navigation_view.visibility =  View.VISIBLE
            }
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return mNavController.navigateUp(mAppBarConfiguration) || super.onSupportNavigateUp()
//    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}
