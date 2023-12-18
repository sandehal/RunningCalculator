package com.example.myfinance.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myfinance.mitm.RunConverterViewModel
import com.example.myfinance.screens.HomeScreen
import com.example.myfinance.screens.IntSpeedScreen
import com.example.myfinance.screens.TsdInitiate

@Composable
fun BootScreen(navController: NavHostController){
    NavHost(navController = navController, startDestination = "IntSpeedScreen") {
        //Legg til nye skjermer her
        composable(Screen.HomeScreen.route) { HomeScreen(navController) }
        composable(Screen.IntSpeedScreen.route) { IntSpeedScreen(navController, RunConverterViewModel()) }
        composable(Screen.TsdConversionScreen.route) { TsdInitiate(navController) }
    }
}