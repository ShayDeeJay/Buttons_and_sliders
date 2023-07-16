package app.game.buttonsandsliders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import app.game.buttonsandsliders.ui.theme.ButtonsAndSlidersTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ButtonsAndSlidersTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    CompositionLocalProvider(
                        LocalOverscrollConfiguration provides null
                    ) {
                        LazyColumn() {
                            item {
                                Column(
                                    horizontalAlignment = Start,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        modifier = Modifier.padding(10.dp),
                                        text = "Swipe Actions",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                    Text(
                                        modifier = Modifier.padding(10.dp),
                                        text = "Swipes to the end, if it reaches the end, it can complete some action",
                                        fontSize = 12.sp,
                                    )
                                    Greeting()
                                    Divider(modifier = Modifier.fillMaxWidth())
                                }
                            }

                            item {
                                Column(
                                    horizontalAlignment = Start,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        modifier = Modifier.padding(10.dp),
                                        text = "Sliders",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                    Text(
                                        modifier = Modifier.padding(10.dp),
                                        text = "Sliders have on/off state, can add content to it to define" +
                                                "it current state life shown in Slider 2",
                                        fontSize = 12.sp,
                                    )
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Card {
                                            Column(
                                                modifier = Modifier.padding(5.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Text(
                                                    text = "Slider 1",
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                Slider(80.dp)
                                            }
                                        }

                                        Card {
                                            Column(
                                                modifier = Modifier.padding(5.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Text(
                                                    text = "Slider 2",
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                SliderWithSelection(80.dp)
                                            }
                                        }

                                        Card {
                                            Column(
                                                modifier = Modifier.padding(5.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Text(
                                                    text = "Slider 3",
                                                    fontSize = 12.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                                SliderWithSelectionIcon(80.dp)
                                            }
                                        }
                                    }
                                    Divider(modifier = Modifier.fillMaxWidth())
                                }
                            }

                            item {
                                Column(
                                    horizontalAlignment = Start,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        modifier = Modifier.padding(10.dp),
                                        text = "Swipe Actions",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                    Text(
                                        modifier = Modifier.padding(10.dp),
                                        text = "Swipes to the end, if it reaches the end, it can complete some action",
                                        fontSize = 12.sp,
                                    )
                                    SwipeSample()
                                    Divider(modifier = Modifier.fillMaxWidth())
                                }

                            }

                            item {
                                Column(
                                    horizontalAlignment = Start,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        modifier = Modifier.padding(10.dp),
                                        text = "Swipe Actions",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                    Text(
                                        modifier = Modifier.padding(10.dp),
                                        text = "Swipes to the end, if it reaches the end, it can complete some action",
                                        fontSize = 12.sp,
                                    )
                                    SwipeAnyPercent()
                                    Divider(modifier = Modifier.fillMaxWidth())
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
fun Greeting(
    modifier: Modifier = Modifier
) {
    val localConfig = LocalConfiguration.current
    val width = localConfig.screenWidthDp.dp-20.dp
    val animationTween = 300
    var toggle by remember {
        mutableStateOf(false)
    }
    val derivedFlick by remember {
        derivedStateOf { toggle }
    }
    val animateWidth by animateFloatAsState(
        targetValue = if (!derivedFlick) 0f else 0.50f,
        tween(animationTween)
    )

    val animateColour by animateColorAsState(
        targetValue = if (!derivedFlick) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.primary,
        tween(animationTween)
    )

    val animateColour2 by animateColorAsState(
        targetValue = if (!derivedFlick)  MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background,
        tween(animationTween)
    )
    Column{
        Card(
            modifier = Modifier
                .padding(10.dp)
                .height(50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(1000.dp)
        ) {

            BoxWithConstraints{
                Box {
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
                            elevation = CardDefaults.cardElevation(10.dp)
                        ) {
                            Text(text = "")
                        }
                    }
                }

                Box {
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
                        ) {
                            Text(text = "First", color = animateColour, fontWeight = FontWeight.Bold)
                        }

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
                        ) {
                            Text(text = "Second", color = animateColour2, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Slider(size: Dp) {

    var sliderColor by remember { mutableStateOf(false) }

    val derivedColour by remember { derivedStateOf{ sliderColor } }

    val animateSlider by animateColorAsState(
        targetValue = if(derivedColour) MaterialTheme.colorScheme.primary else Color.LightGray,
        tween(300)
    )
    val animateWidth by animateFloatAsState(
        targetValue = if (!derivedColour) 0f else 0.50f,
        tween(300)
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
                modifier = Modifier.fillMaxWidth(),
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
        }
    }
}

@Composable
fun SliderWithSelection(size: Dp) {

    var sliderColor by remember { mutableStateOf(false) }

    val derivedColour by remember { derivedStateOf{ sliderColor } }

    val animateSlider by animateColorAsState(
        targetValue = if(derivedColour) MaterialTheme.colorScheme.primary else Color.LightGray,
        tween(300)
    )
    val animateWidth by animateFloatAsState(
        targetValue = if (!derivedColour) 0f else 0.50f,
        tween(300)
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
                modifier = Modifier.fillMaxWidth(),
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

            Card(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(Color.Transparent)
            ){
                AnimatedVisibility(
                    visible = derivedColour,
                    enter = slideInHorizontally(tween(300))
                            + fadeIn(tween(300)),
                    exit = slideOutHorizontally(tween(300))
                            + fadeOut(tween(100))
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                       horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Spacer(
                            modifier = Modifier.width(15.dp)
                        )
                        Text(
                            text = "ON",
                            fontSize = (size.value / 6).sp,
                            color = MaterialTheme.colorScheme.background,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }


            Card(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(Color.Transparent)
            ){
                AnimatedVisibility(
                    visible = !derivedColour,
                    enter = slideInHorizontally(
                        tween(250),
                        initialOffsetX = { initialOffset -> +initialOffset }
                    ) + fadeIn(tween(300)),
                    exit = slideOutHorizontally(
                        tween(300),
                        targetOffsetX = { targetOffset -> +targetOffset }
                    ) + fadeOut(tween(100))
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = "OFF",
                            fontSize = (size.value / 6).sp,
                            color = MaterialTheme.colorScheme.background,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(
                            modifier = Modifier.width(15.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SliderWithSelectionIcon(size: Dp) {

    var sliderColor by remember { mutableStateOf(false) }

    val derivedColour by remember { derivedStateOf{ sliderColor } }

    val animateSlider by animateColorAsState(
        targetValue = if(derivedColour) MaterialTheme.colorScheme.primary else Color.LightGray,
        tween(300)
    )
    val animateWidth by animateFloatAsState(
        targetValue = if (!derivedColour) 0f else 0.50f,
        tween(300)
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
                modifier = Modifier.fillMaxWidth(),
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

            Card(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(Color.Transparent)
            ){
                AnimatedVisibility(
                    visible = derivedColour,
                    enter = slideInHorizontally(tween(300))
                            + fadeIn(tween(300)),
                    exit = slideOutHorizontally(tween(300))
                            + fadeOut(tween(100))
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Spacer(
                            modifier = Modifier.width(15.dp)
                        )
                        Icon(
                            imageVector = Icons.Filled.DarkMode,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }
            }


            Card(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(Color.Transparent)
            ){
                AnimatedVisibility(
                    visible = !derivedColour,
                    enter = slideInHorizontally(
                        tween(250),
                        initialOffsetX = { initialOffset -> +initialOffset }
                    ) + fadeIn(tween(300)),
                    exit = slideOutHorizontally(
                        tween(300),
                        targetOffsetX = { targetOffset -> +targetOffset }
                    ) + fadeOut(tween(100))
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            imageVector = Icons.Filled.LightMode,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background
                        )

                        Spacer(
                            modifier = Modifier.width(15.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SwipeSample() {
    var offsetX by remember { mutableFloatStateOf(0f) }
    val offsetXDerived by remember { derivedStateOf { offsetX } }
    val sizeAdapt = 80
    val paddingSpace = 7.5
    var size by remember { mutableStateOf(Size.Zero) }
    val dpToDp = with(LocalDensity.current) {
        size.width - (sizeAdapt + paddingSpace * 2).dp.toPx()
    }

    val animateSpacer by animateFloatAsState(
        targetValue = offsetXDerived,
        animationSpec = tween(
            easing = LinearOutSlowInEasing
        )
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            Modifier
                .height((sizeAdapt + 20).dp)
                .padding(10.dp)
                .fillMaxWidth()
                .onSizeChanged { size = it.toSize() },
            shape = RoundedCornerShape(20.dp)
        ) {
            Box{
                Box {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Swipe To Action",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            style = TextStyle(
                                brush = Brush.horizontalGradient(
                                    listOf(
                                        Color.Transparent,
                                        Color.Transparent,
                                        MaterialTheme.colorScheme.primary
                                    ),
                                    startX = 0f,
                                    endX = offsetXDerived
                                )
                            )
                        )
                    }
                }

                Box {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        AnimatedVisibility(
                            visible = offsetXDerived >= dpToDp,
                            enter = fadeIn(tween(300)),
                            exit = fadeOut(tween(300))
                        ){
                            Text(
                                text = "Action",
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
                Box {
                    Card(
                        Modifier
                            .padding(paddingSpace.dp)
                            .offset { IntOffset(animateSpacer.roundToInt(), 0) }
                            .size(sizeAdapt.dp)
                            .pointerInput(Unit) {
                                detectDragGestures(
                                    onDragEnd = {
                                        val localSize =
                                            (size.width - (sizeAdapt + paddingSpace * 2).dp.toPx())
                                        offsetX = if (offsetXDerived < localSize) 0f else localSize
                                    }
                                ) { _, dragAmount ->
                                    val original = Offset(offsetXDerived, 0f)
                                    val summed = original + dragAmount
                                    val newValue = Offset(
                                        x = summed.x.coerceIn(
                                            0f,
                                            size.width - (sizeAdapt + paddingSpace * 2).dp.toPx()
                                        ),
                                        y = 0f
                                    )
                                    offsetX = newValue.x
                                }
                            },
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                        elevation = CardDefaults.cardElevation(10.dp),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                modifier = Modifier
                                    .padding(15.dp)
                                    .fillMaxSize(),
                                imageVector = Icons.Filled.ArrowForward,
                                contentDescription = "",
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SwipeAnyPercent() {
    var offsetX by remember { mutableFloatStateOf(0f) }
    var isDragging by remember { mutableStateOf(false) }
    val offsetXDerived by remember { derivedStateOf { offsetX } }
    var size by remember { mutableStateOf(Size.Zero) }
    val valueSize = 30.dp
    val amount = with(LocalDensity.current) { size.width - valueSize.toPx() }

    val animateColour by animateColorAsState(
        targetValue =
        if(isDragging) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.secondary,
        animationSpec = tween(300)
    )

    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Total consumed from available: ",
                fontSize = 14.sp,

            )
            Text(
                text = "(${offsetX.toInt()} / ${amount.toInt()})",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Total in percent: ",
                fontSize = 14.sp,
            )
            Text(
                text = "${((offsetX / amount) * 100).toInt()}%",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            contentAlignment = CenterStart
        ) {

            Card(
                Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(valueSize / 4)
                    .onSizeChanged { size = it.toSize() },
                shape = RoundedCornerShape(20.dp),
                ) {
                val x = MaterialTheme.colorScheme.primary
                Box(
                    modifier = Modifier
                        .fillMaxWidth(convertToFloat((offsetX / amount) * 100))
                        .background(MaterialTheme.colorScheme.primary)
                ) {
                    Text(text = "")
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                Card(
                    modifier = Modifier
                        .offset { IntOffset(offsetX.roundToInt(), 0) }
                        .size(valueSize)
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = { isDragging = true },
                                onDragEnd = { isDragging = false }
                            ) { _, dragAmount ->
                                val original = Offset(offsetXDerived, 0f)
                                val summed = original + dragAmount
                                val newValue = Offset(
                                    x = summed.x.coerceIn(0f, size.width - valueSize.toPx()),
                                    y = 0f
                                )
                                offsetX = newValue.x
                            }
                        },
                    colors = CardDefaults.cardColors(animateColour),
                    elevation = CardDefaults.cardElevation(10.dp),
                    shape = RoundedCornerShape(100f),
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(10.dp),
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background
                        )
                        Icon(
                            modifier = Modifier.size(10.dp),
                            imageVector = Icons.Filled.ArrowForward,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.background
                        )
                    }
                }
            }
        }
    }
}

fun convertToFloat(number: Float): Float {
    return when (number) {
        100.0f -> 1.0f
        else -> (number / 100.0f)
    }
}