package com.example.myfinance.screens.tsdScreens

import Kilometer
import Miles
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myfinance.data.Meter
import com.example.myfinance.mitm.FindSpeedConversionViewModel
import com.example.myfinance.navigation.BottomNavBar
import com.example.myfinance.navigation.TsdNavbar

@Composable
fun FindSpeedScreen(viewModel: FindSpeedConversionViewModel, navController: NavController, tsdNavController: NavController) {

    val output = remember {
        mutableStateOf("Fill in values")
    }


    Column {
        TsdNavbar(navController = tsdNavController)

        TimeEntryFields(viewModel, output)

        DistanceEntryFields(viewModel, output)
        
        Spacer(modifier = Modifier.height(15.dp))

        OutputField(output, viewModel)
    }
    BottomNavBar(navController = navController)
}

@Composable
fun TimeEntryFields(viewModel: FindSpeedConversionViewModel, output: MutableState<String>) {
    var timer: MutableState<String> = remember { mutableStateOf("") }
    var minutter: MutableState<String> = remember { mutableStateOf("") }
    var sekunder: MutableState<String> = remember { mutableStateOf("") }

    Row (Modifier.wrapContentHeight(align = Alignment.CenterVertically)){
        //Timer

        Column(Modifier.weight(1f)) {
            Text(modifier = Modifier
                .padding(4.dp)
                .clip(RoundedCornerShape(20.dp)), text = " Timer", fontSize = 12.sp)
            TextField(modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                placeholder = {Text("00")},
                value = timer.value,
                onValueChange = {
                    timer.value = it.filter { it.isDigit() }.take(2)

                    if (timer.value.isNotEmpty()) {
                        viewModel.ToSpeedConversionUiState.value.timer = timer.value.toInt()
                    }
                    viewModel.CalculateSpeed()
                    output.value = viewModel.ToSpeedConversionUiState.value.displayResult.toString()
                })
        }

        //Minutter
        Column(Modifier.weight(1f)) {
            Text(modifier = Modifier.padding(4.dp), text = " Minutter", fontSize = 12.sp)
            TextField(modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .padding(8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                placeholder = {Text("00")},
                value = minutter.value,
                onValueChange = {
                    minutter.value = it.filter { it.isDigit() }.take(2)

                    if (minutter.value.isNotEmpty()) {
                        viewModel.ToSpeedConversionUiState.value.minutter = minutter.value.toInt()
                    }
                    val antMin = minutter.value.toIntOrNull() ?: 0
                    if (antMin > 59) {
                        minutter.value = "59"
                    }
                    viewModel.CalculateSpeed()
                    output.value = viewModel.ToSpeedConversionUiState.value.displayResult.toString()
                }
            )
        }

        Column(Modifier.weight(1f)) {
            //Sekunder
            Text(modifier = Modifier.padding(4.dp), text = " Sekunder", fontSize = 12.sp)
            TextField(modifier = Modifier
                .padding(8.dp) ,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                placeholder = {Text("00")},
                value = sekunder.value,
                onValueChange = { it ->
                    sekunder.value = it.filter { it.isDigit() }.take(2)
                    if (sekunder.value.isNotEmpty()) {
                        viewModel.ToSpeedConversionUiState.value.sekunder = sekunder.value.toInt()
                    }

                    val antSek = sekunder.value.toIntOrNull() ?: 0
                    if (antSek > 59) {
                        sekunder.value = "59"
                    }
                    viewModel.CalculateSpeed()
                    output.value = viewModel.ToSpeedConversionUiState.value.displayResult.toString()
                }
            )
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DistanceEntryFields(viewModel: FindSpeedConversionViewModel, output: MutableState<String>) {
    var distanse: MutableState<String> = remember { mutableStateOf("") }
    Column {
        Text(text = "Distanse",modifier = Modifier.padding(4.dp), fontSize = 12.sp)
        Row {
            TextField(modifier = Modifier
                .padding(8.dp) ,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                placeholder = {
                    val uiState = viewModel.ToSpeedConversionUiState.collectAsState()
                    Text("00 ${uiState.value.distanseEnhet.repr()}")},

                value = distanse.value, onValueChange = {
                    //Filtering output according to float number rules
                    distanse.value = it.filterIndexed { index, char ->
                        char.isDigit() || (index > 0 && char == '.' && it.indexOf('.') == index && it.count { it == '.' } == 1)
                    }
                    if (distanse.value.isNotEmpty()) {
                        viewModel.ToSpeedConversionUiState.value.distanse = distanse.value.toFloat()
                    }
                    viewModel.CalculateSpeed()
                    output.value = viewModel.ToSpeedConversionUiState.value.displayResult.toString()
                }
            )

            var unit by remember {
                mutableStateOf("Km")
            }

            val unitDistanceMap = hashMapOf<String?, String?>(
            )
            unitDistanceMap["Km"] = "km"
            unitDistanceMap["Miles"] = "mi"
            unitDistanceMap["Meter"] = "me"

            var isExpanded by remember {
                mutableStateOf(false)
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
                    maxLines = 1,
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    textStyle = TextStyle(
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier
                        .menuAnchor()
                        .padding(8.dp)
                )}

                DropdownMenu(
                    expanded = isExpanded,
                    onDismissRequest = {
                        isExpanded = false
                    }
                ) {
                    unitDistanceMap.forEach { el ->
                        DropdownMenuItem(
                            text = {
                                    el.key?.let { Text(text = it) }
                            },
                            onClick = {
                                unit = el.value.toString()
                                val d = viewModel.ToSpeedConversionUiState.value.distanse
                                when(el.key) {
                                    "Km" -> viewModel.ToSpeedConversionUiState.value.distanseEnhet = Kilometer(d)
                                    "Miles" -> viewModel.ToSpeedConversionUiState.value.distanseEnhet = Miles(d)
                                    "Meter" -> viewModel.ToSpeedConversionUiState.value.distanseEnhet = Meter(d)
                                }
                                viewModel.CalculateSpeed()
                                output.value = viewModel.ToSpeedConversionUiState.value.displayResult.toString()
                                isExpanded = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun OutputField(output: MutableState<String>, viewModel: FindSpeedConversionViewModel) {
    
    Column {
        Text(modifier = Modifier.padding(horizontal = 8.dp), text = "FART", style = TextStyle(fontFamily = FontFamily.Monospace))

        Row() {
            TextField(
                modifier = Modifier.padding(horizontal = 8.dp),
                value = output.value, onValueChange = {output.value = it}, readOnly = true
            )
            SelectOutputUnit(output, viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectOutputUnit(output: MutableState<String>, viewModel: FindSpeedConversionViewModel) {
    val unitSpeedList = listOf(
        "Km/h",
        "Mi/h",
        "Min/km",
        "Min/mile"
    )
    val unitSpeedMap = hashMapOf<String?, String?>()
    unitSpeedMap["Km/h"] = "kh"
    unitSpeedMap["Mi/h"] = "mh"
    unitSpeedMap["Min/km"] = "mk"
    unitSpeedMap["Min/mile"] = "mm"



    var unit by remember {
        mutableStateOf("kh")
    }

    var isExpanded by remember {
        mutableStateOf(false)
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
            maxLines = 1,
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            textStyle = TextStyle(
                textAlign = TextAlign.Start
            ),
            modifier = Modifier
                .menuAnchor()
                .padding(horizontal = 8.dp)
        )}

        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = {
                isExpanded = false
            },
            Modifier.wrapContentWidth(Alignment.End)
        ) {
            unitSpeedMap.forEach { el ->
                DropdownMenuItem(
                    text = {
                        Text(text = el.key.toString())
                    },
                    onClick = {
                        unit = el.value.toString()
                        viewModel.ToSpeedConversionUiState.value.displayUnit = el.key.toString()
                        viewModel.CalculateSpeed()
                        output.value = viewModel.ToSpeedConversionUiState.value.displayResult.toString()
                        isExpanded = false
                    }
                )
            }
        }
    }

}