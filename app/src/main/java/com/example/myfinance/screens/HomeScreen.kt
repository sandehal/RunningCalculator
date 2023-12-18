package com.example.myfinance.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myfinance.navigation.BottomNavBar

@Composable
fun HomeScreen(navController: NavController) {

    Column() {
        DashBoard(modifier = Modifier.align(Alignment.CenterHorizontally))

    }
    BottomNavBar(navController = navController)
}

@Composable
fun DashBoard(modifier: Modifier) {
    Box (
        modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Top)){
        Box() {
            Column {
                Text(modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),text = "Velkommen til denne løpekalkulatoren. Her kan du konvertere mellom ulike fartsenheter, og på tvers av fart, tid og distanse.")
            }
        }
    }
}