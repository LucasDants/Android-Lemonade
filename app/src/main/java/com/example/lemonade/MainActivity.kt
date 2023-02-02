package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp() {
    LemonadeButton()
}

@Composable
fun LemonadeButton() {
    var step by remember {
        mutableStateOf(1)
    }

    var squeezed by remember {
        mutableStateOf((2..4).random())
    }

    val text = when(step) {
        1 -> R.string.tap_lemon_tree
        2 -> R.string.tap_lemon
        3 -> R.string.tap_lemonade
        else -> R.string.tap_empty_glass
    }

    val contentDescription = when(step) {
        1 -> R.string.lemon_tree_content_description
        2 -> R.string.lemon_content_description
        3 -> R.string.lemonade_glass_content_description
        else -> R.string.empty_glass_content_description
    }

    val image = when(step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(id = text), fontSize = 18.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            when(step) {
                1 -> step++
                2 -> if(squeezed - 1 == 0) {
                    step++
                    squeezed = (2..4).random()
                } else  squeezed--
                3 -> step++
                else -> step = 1
            }
        }, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent), modifier = Modifier.border(width = 2.dp, color = Color(105, 205, 216), shape = RoundedCornerShape(4.dp))) {
            Image(painter = painterResource(id = image), contentDescription = stringResource(id = contentDescription) )
        }
    }

}
