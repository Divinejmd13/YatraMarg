package com.example.learndapp.Homepage

import android.annotation.SuppressLint
import androidx.compose.ui.Alignment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.learndapp.R
import com.example.learndapp.data.JourneyDetails
import com.example.learndapp.data.Stop
import com.example.learndapp.data.generateRandomStops
import kotlin.math.roundToInt
class JHome() {
    companion object {
        var journeyDetails=JourneyDetails("",SnapshotStateList<Stop>() )
        var kilo=true
    }
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState",
        "MutableCollectionMutableState"
    )
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun homeScreen(navController: NavController){
        var destination by remember { mutableStateOf("Destination") }
        var stops by remember { mutableStateOf(SnapshotStateList<Stop>()) }
        var showInKilometers by remember { mutableStateOf(true) }
        Scaffold(
            bottomBar= {
                BottomAppBar(
                    containerColor = Color.White,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF129CED), // Set your desired background color
                            contentColor = Color.White // Set your desired text color
                        ),
                        onClick = {navController.navigate("second")},
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 10.dp)
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Text(
                            text = "Start Journey",
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
                            text = "Yatraमार्ग ",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 23.sp,
                                fontWeight = FontWeight.W100,
                                fontFamily = FontFamily.Serif,
                            )
                        )

                    },

                    navigationIcon = {

                        IconButton(onClick = { /* do something */ }) {
                            Icon( Icons.Default.Menu,
                                contentDescription = "Drawer",
                                tint = Color.Black,
                                modifier=Modifier.size(30.dp))
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* do something */ }) {
                            Icon( Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.Black,
                                modifier=Modifier.size(30.dp))
                        }
                    }
                )


            }
        ) {
            //content


            Column(modifier = Modifier
                .fillMaxHeight()
                .padding(it)
            ) {
                Column(modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()  ){
                    Text(
                        text = "Select Destination",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Light,
                            fontFamily = FontFamily(Font(R.font.fallingsky))
                        ),
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                    destination = selectDes { stops = generateRandomStops()
                                                journeyDetails= JourneyDetails(
                                                    destination = destination,
                                                    stops =stops
                                                )
                                                kilo=showInKilometers
                    }

                }

                Text(
                    text = "Route Details",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        fontFamily = FontFamily(Font(R.font.fallingsky))
                    ),
                    modifier = Modifier.padding(10.dp)
                )

                Row(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth(),
                )
                {
                    Box(
                        modifier = Modifier
                            .size(65.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .background(color = Color(0xFFe7f0f4))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.bus),
                            contentDescription = "Localized description",
                            modifier = Modifier
                                .size(50.dp)
                                .align(Alignment.Center)

                        )
                    }
                    Column{
                        Row {
                            Text(
                                text = "Destination :",
                                style = TextStyle(
                                    color = Color.DarkGray,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.ExtraLight,
                                    fontFamily = FontFamily(Font(R.font.fallingsky))
                                ),
                                modifier = Modifier.padding(10.dp)
                            )
                            Text(
                                text = destination,
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Light,
                                    fontFamily = FontFamily(Font(R.font.fallingsky))
                                ),
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        if (stops.isNotEmpty()) {
                            Row {
                                Text(
                                    text = if (showInKilometers)
                                        "${stops.last().preSum.roundToInt()} km"
                                    else
                                        "${convertKilometersToMiles(stops.last().preSum).roundToInt()} miles",
                                    modifier = Modifier.padding(horizontal = 10.dp),
                                    style = TextStyle(color = Color.DarkGray)
                                )
                                Text(
                                    text = "Estimated time: ${stops.last().preTimesum.roundToInt()} min",
                                    modifier = Modifier.padding(horizontal = 10.dp),
                                    style = TextStyle(color = Color.DarkGray)
                                )
                            }
                        }

                    }
                }
                Button(
                    onClick = { showInKilometers = !showInKilometers
                              kilo=showInKilometers},
                    modifier = Modifier
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF129CED), // Set your desired background color
                        contentColor = Color.White // Set your desired text color
                    )

                ) {
                    Text(
                        text = if (showInKilometers) "Show in Miles" else "Show in Kilometers",
                        style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            letterSpacing = 1.25.sp,
                            color = Color.White
                        )
                    )
                }


                Text(
                    text = "Upcoming Stops",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        fontFamily = FontFamily(Font(R.font.fallingsky))
                    ),
                    modifier = Modifier.padding(10.dp)
                )

                Column(modifier = Modifier.padding(10.dp),
                    ) {

                    LazyColumn(Modifier){
                        itemsIndexed(stops){ index,item ->

                            Column(

                                modifier = Modifier.padding(10.dp)  
                                  ) {
                                Row(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .fillMaxWidth(),
                                )
                                {
                                    Box(
                                        modifier = Modifier
                                            .size(65.dp)
                                            .clip(RoundedCornerShape(18.dp))
                                            .background(color = Color(0xFFe7f0f4))
                                          
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.location),
                                            contentDescription = "location",
                                            modifier = Modifier
                                                .size(40.dp)
                                                .align(Alignment.Center),
                                            tint = Color.Black
                                        )
                                    }
                                    Column{
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
                                                modifier = Modifier.padding(10.dp)
                                            )
                                        }
                                        Row {
                                            Text(
                                                text = if (showInKilometers)
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

                                    
                                }

                            }


                        }

                    }



                }

            }
        }
    }



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun selectDes(onClickAction: () -> Unit): String {
        var text by remember { mutableStateOf("") }
        var openMenu by remember { mutableStateOf(false) }
        val places = listOf("Ayodhya", "Varanasi", "Haridwar", "Mathura", "Vrindavan", "Prayagraj", "Dwaraka", "Rishikesh")

        OutlinedTextField(
            value = text,
            onValueChange = { newText ->
                text = newText
                openMenu = true

            },
            label = { Text("Select Destination") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            trailingIcon = {
                if (text.isNotEmpty()) {
                    IconButton(
                        onClick = {
                            text = ""
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
            },
            keyboardActions= KeyboardActions(onDone = { onClickAction.invoke() }),

            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = Color(0xFF000000),
                unfocusedLabelColor = Color.Gray,
                focusedBorderColor = Color(0xFF000000),
                unfocusedBorderColor = Color.Gray,
            )
        )

        if (openMenu) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
                    .clip(RoundedCornerShape(5.dp))
            ) {
                LazyColumn {
                    items(places.filter { it.contains(text, ignoreCase = true) }.take(5)) { suggestion ->
                        Text(
                            text = suggestion,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    text = suggestion
                                    openMenu = false
                                    onClickAction.invoke()
                                }
                                .padding(horizontal = 15.dp, vertical = 8.dp)
                                .offset(x = -5.dp)
                        )
                    }
                }
            }
        }
        return text
    }

    @Preview
    @Composable
    fun preview(){
        val navController = rememberNavController()

        homeScreen(navController)
    }


}