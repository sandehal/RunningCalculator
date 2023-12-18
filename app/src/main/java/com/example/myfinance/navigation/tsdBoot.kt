package com.example.myfinance.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myfinance.mitm.DistanceConversionViewModel
import com.example.myfinance.screens.tsdScreens.FindDistanceScreen
import com.example.myfinance.screens.tsdScreens.FindSpeedScreen
import com.example.myfinance.screens.tsdScreens.FindTimeScreen

@Composable
fun TsdBootScreen(navController: NavController, tsdNavController: NavHostController){
    NavHost(navController = tsdNavController, startDestination = "FindSpeedScreen") {
        //Legg til nye skjermer her
        composable(ConversionScreen.FindSpeed.route) { FindSpeedScreen(DistanceConversionViewModel(), navController, tsdNavController) }
        composable(ConversionScreen.FindDistance.route) {FindDistanceScreen(navController, tsdNavController)}
        composable(ConversionScreen.FindTime.route) {FindTimeScreen(navController, tsdNavController)}
    }
}