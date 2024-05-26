package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonadeApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    var screenNumber by remember { mutableIntStateOf(0) }
    var clicks by remember { mutableIntStateOf(0) }

    val imageToShow = when (screenNumber) {
        0 -> painterResource(id = R.drawable.lemon_tree)
        1 -> painterResource(id = R.drawable.lemon_squeeze)
        2 -> painterResource(id = R.drawable.lemon_drink)
        else -> painterResource(id = R.drawable.lemon_restart)
    }
    val textToShow = when (screenNumber) {
        0 -> stringResource(R.string.lemon_tree)
        1 -> stringResource(R.string.lemon_squeeze)
        2 -> stringResource(R.string.lemon_drink)
        else -> stringResource(R.string.lemon_restart)
    }
    Column(
        modifier = modifier.fillMaxSize().background(Color.hsl(70f, 0.5f, 0.9f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = imageToShow,
            contentDescription = textToShow,
            modifier = Modifier
                .padding(16.dp)
                .size(300.dp)
                .background(Color.hsl(70f, 0.5f, 1f), shape = RoundedCornerShape(20.dp))
                .clickable {
                     if (screenNumber == 1){
                         // wait till the user clicks 3 times
                         if (clicks == 3) {
                             screenNumber = (screenNumber + 1) % 4
                             clicks = 0
                         }
                         clicks++
                     } else {
                         screenNumber = (screenNumber + 1) % 4
                     }
                }
        )
        Text(text = textToShow, modifier = Modifier.padding(16.dp), fontSize = 24.sp, textAlign = TextAlign.Center)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            LemonadeApp(modifier = Modifier.padding(innerPadding))
        }
    }
}