package com.example.myfinance.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.navigation.BottomNavBar
import com.example.myfinance.navigation.TsdBootScreen
import com.example.myfinance.navigation.TsdNavbar

@Composable
fun TsdInitiate(navController: NavController) {

    val tsdNavController = rememberNavController()
    TsdBootScreen(navController, tsdNavController = tsdNavController)

}

//Dropdown menu to select what type of unit to use in the conversion
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectUnitWithList(list: List<String>) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var unit by remember {
        mutableStateOf("")
    }

    Column {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { newValue ->
                isExpanded = newValue
            }, Modifier.padding(3.dp)
        ) { TextField(
            value = unit,
            onValueChange = {
            },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            placeholder = {
                var text = ""
                Text(text = text)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )
        }

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            list.forEach { el ->
                DropdownMenuItem(
                    text = {
                        Text(text = el)
                    },
                    onClick = {
                        unit = el
                        isExpanded = false
                    }
                )
            }
        }
    }

}