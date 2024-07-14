package com.example.learndapp.Homepage

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learndapp.Homepage.JHome.Companion.journeyDetails
import com.example.learndapp.Homepage.JHome.Companion.kilo
import com.example.learndapp.R
import com.example.learndapp.data.Stop
import kotlin.math.roundToInt

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun secondScreen(navController: NavController) {
    var load by remember { mutableFloatStateOf(0f) }
    var indeces by remember {
        mutableStateOf(-1)
    }
    var selectedFilters by remember { mutableStateOf(listOf<String>()) }
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {

                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF129CED), // Set your desired background color
                        contentColor = Color.White // Set your desired text color
                    ),
                    onClick = {
                        try {
                            indeces += 1
                            val lastStop = journeyDetails.stops.last()
                            val currentStop = journeyDetails.stops[indeces]
                            load = (((currentStop.preSum / lastStop.preSum)).toFloat())
                            selectedFilters=selectedFilters + journeyDetails.stops[indeces].name
                        } catch (e: IndexOutOfBoundsException) {


                        }

                    },
                    modifier = Modifier
                        .padding(vertical = 10.dp, horizontal = 10.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = "I've reached my next stop",
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            letterSpacing = 1.25.sp,
                            color = Color.White
                        )
                    )
                }
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "मार्ग ",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.W100,
                            fontFamily = FontFamily.Serif,
                        ),
                        modifier = Modifier.padding(horizontal = 5.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Handle search button click */ }) {
                        Icon(
                            Icons.Default.Search,
                            contentDescription = "Search",
                            tint = Color.Black,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            )
        }
    ) {

        if(load*100 ==100f){
            CompletionDialog {
                navController.popBackStack()
            }
        }

        Column(modifier = Modifier.padding(it)) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Progress",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        fontFamily = FontFamily(Font(R.font.fallingsky))
                    ),
                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp)
                )
                LinearProgressIndicator(
                    color = Color(0xFF129CED),
                    progress = load,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                )
                Row(modifier = Modifier,
                    horizontalArrangement = Arrangement.Absolute.Right){

                    if (indeces >= 0) {
                        Text(
                            text = if (kilo) {
                                "Distance Left: ${
                                    ((journeyDetails.stops.last().preSum) - (journeyDetails.stops[indeces].preSum)).roundToInt()
                                } km"
                            } else {
                                "Distance Left: ${
                                    convertKilometersToMiles((journeyDetails.stops.last().preSum) - (journeyDetails.stops[indeces].preSum)).roundToInt()
                                } miles"
                            },
                            modifier = Modifier.padding(horizontal = 10.dp),
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily.SansSerif
                            )
                        )
                    } else {
                        Text(
                            text = if (kilo) {
                                "Distance Left: ${(journeyDetails.stops.last().preSum).roundToInt()} Km"
                            } else {
                                "Distance Left: ${convertKilometersToMiles(journeyDetails.stops.last().preSum).roundToInt()} miles"
                            },
                            modifier = Modifier.padding(horizontal = 10.dp),
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal,
                                fontFamily = FontFamily.SansSerif
                            )
                        )
                    }


                }
                Column(
                    modifier = Modifier.padding(0.dp),
                ) {

                    LazyColumn(Modifier) {
                        itemsIndexed(journeyDetails.stops) { index, item ->
                            Card(
                                colors = CardDefaults.cardColors(
                                    containerColor=Color.White
                                ),
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(45.dp)
                                            .clip(RoundedCornerShape(18.dp))
                                            .background(color = Color(0xFFe7f0f4))
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.location),
                                            contentDescription = "location",
                                            modifier = Modifier
                                                .size(30.dp)
                                                .align(Alignment.Center),
                                            tint = Color.Black
                                        )
                                    }
                                    Column {
                                        Row {
                                            Text(
                                                text = "Location :",
                                                style = TextStyle(
                                                    color = Color.DarkGray,
                                                    fontSize = 18.sp,
                                                    fontWeight = FontWeight.ExtraLight,
                                                    fontFamily = FontFamily(Font(R.font.fallingsky))
                                                ),
                                                modifier = Modifier.padding(10.dp)
                                            )
                                            Text(
                                                text = item.name,
                                                style = TextStyle(
                                                    color = Color.Black,
                                                    fontSize = 18.sp,
                                                    fontWeight = FontWeight.Light,
                                                    fontFamily = FontFamily(Font(R.font.fallingsky))
                                                ),
                                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 10.dp)
                                            )
                                        }
                                        Row {
                                            Text(
                                                text = if (kilo)
                                                    "${item.preSum.roundToInt()} km"
                                                else
                                                    "${convertKilometersToMiles(item.preSum).roundToInt()} miles",
                                                modifier = Modifier.padding(horizontal = 10.dp),
                                                style = TextStyle(color = Color.DarkGray)
                                            )
                                            Text(
                                                text = "${item.preTimesum.roundToInt()} min",
                                                modifier = Modifier.padding(horizontal = 10.dp),
                                                style = TextStyle(color = Color.DarkGray)
                                            )
                                        }
                                    }
                                    FilterChip(
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = Color(0xFF129CED),
                                            selectedLabelColor = Color.White,
                                            labelColor = Color.Black,
                                            disabledContainerColor = Color.Gray,
                                            disabledLabelColor = Color.Black,
                                            containerColor = Color(0xFFE6EFF3),
                                            disabledSelectedContainerColor = Color.Gray.copy(alpha = 0.5f),
//
                                        ),
                                        modifier = Modifier
                                            .padding(horizontal = 7.dp)
                                            .align(Alignment.CenterVertically),
                                        onClick = {
                                            try{
                                                if (item.name !in selectedFilters) {

                                                    for (i in 0 until index+1) {
                                                        selectedFilters = selectedFilters + item.name
                                                        val stopAbove = journeyDetails.stops[i]
                                                        if (stopAbove.name !in selectedFilters) {
                                                            selectedFilters =
                                                                selectedFilters + stopAbove.name
                                                        }
                                                        indeces = i
                                                        val lastStop = journeyDetails.stops.last()
                                                        val currentStop =
                                                            journeyDetails.stops[i]
                                                        load =
                                                            (((currentStop.preSum / lastStop.preSum)).toFloat())
                                                    }
                                                }
                                            }catch (_:IndexOutOfBoundsException){}
                                        },
                                        label = {
                                            Text("Reached", modifier = Modifier.padding(5.dp))
                                        },
                                        selected = item.name in selectedFilters,
                                        leadingIcon = if (item.name in selectedFilters) {
                                            {
                                                Icon(
                                                    imageVector = Icons.Filled.Done,
                                                    contentDescription = "Done icon",
                                                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                                                )
                                            }
                                        } else {
                                            null
                                        },
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun CompletionDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200 .dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Image(painter = painterResource(id = R.drawable.reached), contentDescription = "Congratulation Icon",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
                    .align(Alignment.CenterHorizontally)
                   
            )

        }
    }
}

 fun convertKilometersToMiles(kilometers: Double): Double {

    return kilometers * 0.621371
}

@Composable
@Preview
fun presec() {
    val navController = rememberNavController()
 secondScreen(navController = navController)
}
