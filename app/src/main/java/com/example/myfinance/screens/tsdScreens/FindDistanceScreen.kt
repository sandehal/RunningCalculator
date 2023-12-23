package com.example.myfinance.screens.tsdScreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myfinance.navigation.BottomNavBar
import com.example.myfinance.navigation.TsdNavbar

@Composable
fun FindDistanceScreen(navController: NavController, tsdNavController: NavController) {
    Column {
        TsdNavbar(navController = tsdNavController)

    }
    BottomNavBar(navController = navController)
}

