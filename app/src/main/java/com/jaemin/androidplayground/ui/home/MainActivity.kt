package com.jaemin.androidplayground.ui.home

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.jaemin.androidplayground.R
import com.jaemin.androidplayground.databinding.ActivityMainBinding
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // BottomNavigation 설정
        binding.bottomNav.setupWithNavController(navController)

        // AppBar 설정
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.map_fragment,
            R.id.second_fragment,
            R.id.third_fragment
        ))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}