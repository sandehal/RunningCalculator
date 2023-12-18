package com.example.myfinance.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.twotone.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myfinance.ui.theme.navBarColor

sealed class ConversionScreen(val route: String, val icon: ImageVector, val description: String) {
    object FindSpeed : ConversionScreen("FindSpeedScreen", Icons.Default.AddCircle, "Find speed")
    object FindDistance : ConversionScreen("FindDistanceScreen", Icons.Default.AddCircle, "Find distance")
    object FindTime : ConversionScreen("FindTimeScreen", Icons.Default.AddCircle, "Find time")
}

@Composable
fun TsdNavbar(navController: NavController){
    val items = listOf(
        ConversionScreen.FindSpeed,
        ConversionScreen.FindDistance,
        ConversionScreen.FindTime
    )


    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .padding(8.dp)
            .clip(RoundedCornerShape(20.dp)),
        containerColor = navBarColor,

        ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEachIndexed { _, item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                icon = { Icon(item.icon, contentDescription = item.route,
                    tint = if (currentDestination?.hierarchy?.any { it.route == item.route } == true) Color.Black else Color.White) },
                label = { Text(text = item.description, color = Color.White, fontSize = 14.sp) },
                onClick = { if (currentDestination?.hierarchy?.any { it.route == item.route} == true) {
                } else {
                    navController.navigate(item.route)
                    {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
                }
            )
        }
    }
}