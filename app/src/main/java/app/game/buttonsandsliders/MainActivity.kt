package app.game.buttonsandsliders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOut
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.game.buttonsandsliders.ui.theme.ButtonsAndSlidersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ButtonsAndSlidersTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Greeting()
                        Slider(80.dp)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(modifier: Modifier = Modifier) {
    val localConfig = LocalConfiguration.current
    val width = localConfig.screenWidthDp.dp-20.dp
    var toggle by remember {
        mutableStateOf(false)
    }
    val derivedFlick by remember {
        derivedStateOf { toggle }
    }
    val animateWidth by animateFloatAsState(
        targetValue = if (!derivedFlick) 0f else 0.50f,
        tween(500)
    )

    val animateColour by animateColorAsState(
        targetValue = if (!derivedFlick) Color.DarkGray else MaterialTheme.colorScheme.primary,
        tween(500)
    )

    val animateColour2 by animateColorAsState(
        targetValue = if (!derivedFlick)  MaterialTheme.colorScheme.primary else Color.DarkGray,
        tween(500)
    )
    Column(){
        Card(
            modifier = Modifier
                .padding(10.dp)
                .height(50.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.DarkGray),
            shape = RoundedCornerShape(1000.dp)
        ) {

            BoxWithConstraints(){


                Box() {
                    Row(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.fillMaxWidth(animateWidth))

                        Card(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(width / 2),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                            shape = RoundedCornerShape(1000.dp),
                            elevation = CardDefaults.cardElevation(5.dp)
                        ) {
                            Text(text = "")
                        }

                    }
                }

                Box() {
                    Row(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        Column(
                            modifier = Modifier
                                .width(width / 2)
                                .fillMaxHeight()
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onTap = {
                                            toggle = false
                                        }
                                    )
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) { Text(text = "First", color = animateColour) }
                        Column(
                            modifier = Modifier
                                .width(width / 2)
                                .fillMaxHeight()
                                .pointerInput(Unit) {
                                    detectTapGestures(
                                        onTap = {
                                            toggle = true
                                        }
                                    )
                                },
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) { Text(text = "Second", color = animateColour2) }
                    }
                }
            }
        }
    }
}

@Composable
fun Slider(size: Dp){
    var sliderColor by remember {
        mutableStateOf(false)
    }
    val derivedColour by remember {
        derivedStateOf{
            sliderColor
        }
    }

    val animateSlider by animateColorAsState(
        targetValue = if(derivedColour) MaterialTheme.colorScheme.primary else Color.LightGray,
        tween(500)
    )
    val animateWidth by animateFloatAsState(
        targetValue = if (!derivedColour) 0f else 0.50f,
        tween(500)
    )

    Card(
        modifier = Modifier
            .padding(10.dp)
            .width(size)
            .height(size / 2)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        sliderColor = !sliderColor
                    }
                )
            },
        colors = CardDefaults.cardColors(Color.DarkGray),
//        border = BorderStroke((size / 2) / 10,Color.DarkGray),
        shape = RoundedCornerShape(size)
    ) {
        BoxWithConstraints(){
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding((size / 2) / 10),
                colors = CardDefaults.cardColors(animateSlider),
                shape = RoundedCornerShape(size)
            ) {

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Spacer(modifier = Modifier.fillMaxWidth(animateWidth))
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(size / 2)
                        .padding((size) / 10),
                    colors = CardDefaults.cardColors(Color.Gray),
                    shape = RoundedCornerShape(size),
                    elevation = CardDefaults.cardElevation(5.dp)
                ) {
                    Text(text = "")
                }
            }

            Row(
                modifier = Modifier.padding((size / 2) / 10).fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .width(size / 2)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) { AnimatedVisibility(
                    visible = derivedColour,
                    enter = fadeIn(tween(500)),
                    exit = fadeOut(tween(300))
                ){ Text(text = "ON", fontSize = (size.value/6).sp,color = Color.DarkGray,fontWeight = FontWeight.Bold) } }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .width(size / 2)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.Center
                ) { AnimatedVisibility(
                    visible = !derivedColour,
                    enter = fadeIn(tween(500)),
                    exit = fadeOut(tween(300))
                ){ Text(text = "OFF", fontSize = (size.value/6).sp, color = Color.DarkGray,fontWeight = FontWeight.Bold) } }
            }
        }
    }
}