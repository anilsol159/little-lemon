package com.example.littlelemon

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navHostController: NavHostController){
    val context = LocalContext.current
    var sharedPreferences: SharedPreferences = context.getSharedPreferences("Login", Context.MODE_PRIVATE)
    var startDestination = if(sharedPreferences.getBoolean("Logged",false)) Home.route else Onboarding.route
    NavHost(navController = navHostController, startDestination = startDestination){
        composable(Home.route){
            Home(navHostController)
        }
        composable(Profile.route){
            Profile(navHostController)
        }
        composable(Onboarding.route){
            Onboarding(navHostController)
        }
    }
}