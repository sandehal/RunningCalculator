package com.example.myfinance.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myfinance.mitm.RunConverterViewModel
import com.example.myfinance.navigation.BottomNavBar
import com.example.myfinance.navigation.TsdBootScreen
import com.example.myfinance.navigation.TsdNavbar
import com.example.myfinance.ui.theme.mainbackgroundColor

@Composable
fun IntSpeedScreen(navController: NavController,viewModel: RunConverterViewModel) {
    val uiState = viewModel.runConverterUiState.collectAsState()
    val output = remember {
        mutableStateOf("")
    }

    val reprString = remember {
        mutableStateOf("")
    }

    Column {
        Row(Modifier.background(mainbackgroundColor)) {
            Column(
                Modifier
                    .width(200.dp)
                    .padding(10.dp)) {
                FromSpeedUnitDropdownMenu(1, viewModel)
                Spacer(modifier = Modifier.height(20.dp))
                FromSpeedUnitDropdownMenu(2, viewModel)
            }

            Column(Modifier.padding(10.dp)) {
                UnitValueInputBox(viewModel, output, reprString)
                Button(onClick = {
                    TODO("Not correctly functioning")
                    viewModel.reverseUnits()
                    viewModel.createUnit(viewModel.runConverterUiState.value.fraEnhet)
                    viewModel.convertUnit()
                    output.value = viewModel.runConverterUiState.value.tilUnit.toString()
                }) {

                }
            }
        }
        UnitValueOutputBox(output = output)
    }

    BottomNavBar(navController = navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FromSpeedUnitDropdownMenu(from1to2: Int, viewModel: RunConverterViewModel) {
    val unitSpeedList = listOf(
        "km/h",
        "m/h",
        "min/km",
        "min/mile",
        "knots",
        "m/s"
    )
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
            }
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
                when(from1to2) {
                    (1) -> text = "From unit: "
                    (2) -> text = "To unit: "
                }
                Text(text = text)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )}

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            }
        ) {
            unitSpeedList.forEach { el ->
                DropdownMenuItem(
                    text = {
                        Text(text = el)
                    },
                    onClick = {
                        unit = el
                        when(from1to2) {
                            1 -> setUpFromUnit(unit, viewModel)
                            2 -> setUpToUnit(unit, viewModel)
                        }
                        viewModel.createUnit(unit)
                        viewModel.convertUnit()
                        isExpanded = false
                    }
                )
            }
        }
    }

}

//Help function for adding FROM-UNIT
fun setUpFromUnit(unit: String, viewModel: RunConverterViewModel) {
    viewModel.runConverterUiState.value.fraEnhet = unit
}

fun setUpToUnit(unit: String, viewModel: RunConverterViewModel) {
    viewModel.runConverterUiState.value.tilEnhet = unit
}

@Composable
fun UnitValueInputBox(viewModel: RunConverterViewModel, output: MutableState<String>, reprString: MutableState<String>) {
    val uiState = viewModel.runConverterUiState.collectAsState()
    var input by remember {
        mutableStateOf("")
    }


    Column() {
        TextField(keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ) ,placeholder = { Text(text = reprString.value) }, value = input, onValueChange = {
            input = it
            when(viewModel.runConverterUiState.value.fraEnhet) {
                "km/h" ->  viewModel.runConverterUiState.value.fraVerdi = input.toFloatOrNull()
                "m/h" ->  viewModel.runConverterUiState.value.fraVerdi = input.toFloatOrNull()
                "knots" -> viewModel.runConverterUiState.value.fraVerdi = input.toFloatOrNull()
                "m/s" -> viewModel.runConverterUiState.value.fraVerdi = input.toFloatOrNull()
                "min/km" -> {
                    val minsekList = input.split(".")
                    if (minsekList.size == 2 && !minsekList.lastIndex.equals(""))  {
                        viewModel.runConverterUiState.value.minutter = minsekList[0].toIntOrNull()!!
                        viewModel.runConverterUiState.value.sekunder = minsekList[1].toFloatOrNull()
                    }

                }
                "min/mile" -> {
                    val minsekList = input.split(".")
                    if (minsekList.size == 2 && !minsekList.lastIndex.equals(""))  {
                        viewModel.runConverterUiState.value.minutter = minsekList[0].toIntOrNull()!!
                        viewModel.runConverterUiState.value.sekunder = minsekList[1].toFloatOrNull()
                    }
                }
            }

            viewModel.createUnit(viewModel.runConverterUiState.value.fraEnhet)
            viewModel.convertUnit()
            if (uiState.value.fraUnit?.repr() != null) {
                reprString.value = uiState.value.fraUnit!!.repr()
            }
            viewModel.runConverterUiState.value.tilVerdi = viewModel.runConverterUiState.value.tilUnit.toString()
            output.value = viewModel.runConverterUiState.value.tilVerdi
            Log.d("InputBox", viewModel.runConverterUiState.value.tilVerdi)
        })
        Spacer(modifier = Modifier.height(37.dp))
    }

}

@Composable
fun UnitValueOutputBox(output: MutableState<String>) {
    Text(
        output.value,
        fontSize = 50.sp ,
        fontFamily = FontFamily.SansSerif,
        softWrap = true,
        maxLines = 2,    // Limit the text to 2 lines
        overflow = TextOverflow.Ellipsis, // Add ellipsis (...) for overflow
        modifier = Modifier
        .padding(10.dp))
}





