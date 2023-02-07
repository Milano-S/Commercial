package com.exclr8.n1corporate.Activities

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.exclr8.n1corporate.Fragments.Main.CreateOrder.HomeFragment
import com.exclr8.n1corporate.R
import com.exclr8.n1corporate.databinding.ActivityDashboardScreenBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityDashboardScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_dashboard_screen)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_dashboard_screen)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val navController = findNavController(R.id.nav_host_fragment_content_dashboard_screen)
        if (navController.currentDestination?.id == R.id.nav_home){
            HomeFragment().onBackPressed(this){ shouldReturn ->
                if (shouldReturn){
                    super.onBackPressed()
                }
            }
        }else{
            super.onBackPressed()
        }
    }


    fun showAlertDailog(context: Context, callback: (Boolean) -> Unit){
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Are you sure?")
        builder.setMessage("Going back will cancel your current order.")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            callback(true)
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            callback(false)
        }

        builder.show()
    }
}