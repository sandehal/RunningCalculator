package com.example.myfinance.navigation

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myfinance.ui.theme.navBarColor

sealed class Screen(val route: String, val icon: ImageVector, val description: String) {
    object HomeScreen : Screen("HomeScreen", Icons.Default.Info, "Om appen")
    object IntSpeedScreen : Screen("IntSpeedScreen", Icons.Default.Home, "Speed")
    object TsdConversionScreen : Screen("TsdInitiate", Icons.TwoTone.Build, "T - S - D")
}

@Composable
fun BottomNavBar(navController: NavController){
    val items = listOf(
        Screen.HomeScreen,
        Screen.IntSpeedScreen,
        Screen.TsdConversionScreen
    )


    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .wrapContentHeight(align = Alignment.Bottom),
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

/**
 * Hjelplefunksjon som navigerer brukeren til en ny skjerm.
 */
fun navigate(navController: NavController, route: String) {
    navController.navigate(route)
    {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}