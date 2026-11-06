package com.example.firstcomposeproject

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Номер текущего раздела: 0, 1, 2, 3
            var screen by remember { mutableIntStateOf(0) }
            val titles = listOf("Задания 1–2", "Задание 3", "Задание 4", "Задание 5")

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    Button(
                        onClick = { screen = (screen + 1) % titles.size },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text("${titles[screen]}. Нажмите, чтобы переключить")
                    }
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    when (screen) {
                        0 -> TaskOneScreen()
                        1 -> TaskThreeScreen()
                        2 -> TaskFourScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun Hello(name: String?) {
    val text = if (name != null) {
        "Привет, $name!"
    } else {
        "Имя не задано"
    }

    Text(text = text)
}
//Задание 2
@Preview(name = "portrait", showSystemUi = true)
@Composable
fun HelloPortraitPreview() {
    Hello("Ярослав")
}

@Preview(
    name = "landscape",
    showSystemUi = true,
    device = "spec:width=891dp,height=411dp"
)
@Composable
fun HelloLandscapePreview() {
    Hello("Ярослав")
}

@Preview(
    name = "round",
    showSystemUi = true,
    widthDp = 200,
    heightDp = 200,
    showBackground = true,
    backgroundColor = 0xFFFFFF00,
    device = "spec:width=200dp,height=200dp,isRound=true"
)
@Composable
fun HelloRoundPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Hello("Ярослав")
    }
}
@Composable
fun TaskOneScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Hello("Ярослав")
        Hello(null)
    }
}

@Composable
fun TaskThreeScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        JetpackInfoGreen()
        JetpackInfoEllipsis()
        JetpackInfoUnderlined()
    }
}

@Composable
fun TaskFourScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        StyledButton()
    }
}

//Задание 3

// Вариант 1: зелёный цвет, 16.sp, курсив, по центру
@Composable
fun JetpackInfoGreen() {
    Text(
        text = stringResource(R.string.jetpack_info),
        modifier = Modifier.fillMaxWidth(),
        color = Color.Green,
        fontSize = 16.sp,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center
    )
}

// Вариант 2: одна строка, в конце многоточие
@Composable
fun JetpackInfoEllipsis() {
    Text(
        text = stringResource(R.string.jetpack_info),
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

// Вариант 3: отступ сверху, 24.sp, чёрный текст, зелёный фон, подчёркивание
@Composable
fun JetpackInfoUnderlined() {
    Text(
        text = stringResource(R.string.jetpack_info),
        modifier = Modifier
            .padding(top = 48.dp)
            .background(Color.Green),
        color = Color.Black,
        fontSize = 24.sp,
        textDecoration = TextDecoration.Underline
    )
}

@Preview(showBackground = true)
@Composable
fun TextVariantsPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        JetpackInfoGreen()
        JetpackInfoEllipsis()
        JetpackInfoUnderlined()
    }
}

@Composable
fun StyledButton() {
    Button(
        onClick = { },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, Color.Gray),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp
        )
    ) {
        Text("Нажми на меня")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ButtonPreview() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        StyledButton()
    }
}

@Composable
fun InitialsBadge() {
    // Серый контейнер на всю ширину, высотой 100.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        // Круг занимает всю доступную высоту с отступами 16.dp сверху и снизу
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 16.dp)
                .aspectRatio(1f)
                .clip(CircleShape)
                .background(Color(0xFFFF5722)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "АБ", color = Color.Black)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InitialsPreview() {
    InitialsBadge()
}

@Composable
fun MainScreen() {
    // Номер текущего раздела: 0–4 (задания 1–5)
    var screen by remember { mutableIntStateOf(0) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                for (i in 0..3) {
                    Button(
                        onClick = { screen = i },
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(horizontal = 4.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (screen == i) Color.Magenta else Color.LightGray,
                            contentColor = Color.Black
                        )
                    ) {
                        Text("${i + 1}")
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (screen) {
                0 -> TaskOneScreen()
                1 -> TaskThreeScreen()
                2 -> TaskFourScreen()
                3 -> InitialsBadge()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}