package com.example.firstcomposeproject

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Пункт 1: круг исходного размера в правом верхнем углу чёрного блока 240dp x 120dp
@Composable
fun CircleInCorner() {
    Box(
        modifier = Modifier
            .size(240.dp, 120.dp)
            .background(Color.Black),
        contentAlignment = Alignment.TopEnd
    ) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = null
        )
    }
}

// Пункт 2: синий блок, круг по центру, растянут, фиолетовый через colorFilter
@Composable
fun CircleFilled() {
    Box(
        modifier = Modifier
            .size(240.dp, 120.dp)
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.circle),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            colorFilter = ColorFilter.tint(Color(0xFF800080))
        )
    }
}

@Preview(showBackground = true, name = "Задание 6.1")
@Composable
fun CircleInCornerPreview() {
    CircleInCorner()
}

@Preview(showBackground = true, name = "Задание 6.2")
@Composable
fun CircleFilledPreview() {
    CircleFilled()
}

// Простой список контактов в Column (как на первом изображении)
@Composable
fun ContactColumn() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Имя: Ярослав")
        Text("Отчество: Васильевич")
        Text("Фамилия: Давыдов")
        Text("Мобильный телефон: +7 495 495 95 95")
        Text("Адрес: г. Москва, 3-я улица Строителей, д. 25, кв. 12")
    }
}

// Карточка контакта: серый фон, текст по центру
@Composable
fun ContactCard(
    name: String,
    patronymic: String,
    surname: String,
    phone: String,
    address: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color.LightGray)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Имя: $name")
        Text("Отчество: $patronymic")
        Text("Фамилия: $surname")
        Text("Мобильный телефон: $phone")
        Text("Адрес: $address")
    }
}

// Три карточки в Column (как на втором изображении)
@Composable
fun ContactCards() {
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        ContactCard(
            "Ярослав", "Васильевич", "Давыдов", "+7 495 495 95 95",
            "г. Москва, 3-я улица Строителей, д. 25, кв. 12"
        )
        ContactCard(
            "Василий", "Егорович", "Кузнецов", "—",
            "Московская область, дер. Крутово, д. 4"
        )
        ContactCard(
            "Людмила", "Прокофьевна", "Калугина", "+7 495 706 70 70",
            "Москва, Большая Никитская, д. 43, кв. 290"
        )
    }
}

// Строка контакта в Row с иконкой справа
@Composable
fun ContactRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Имя: Ярослав")
            Text("Отчество: Васильевич")
            Text("Фамилия: Давыдов")
            Text("Мобильный телефон: +7 495 495 95 95")
            Text("Адрес: г. Москва, 3-я улица Строителей, д. 25, кв. 12")
        }
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = Color(0xFFFF5722)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "ContactColumn")
@Composable
fun ContactColumnPreview() {
    ContactColumn()
}

@Preview(showBackground = true, showSystemUi = true, name = "ContactCards")
@Composable
fun ContactCardsPreview() {
    ContactCards()
}

@Preview(showBackground = true, showSystemUi = true, name = "ContactRow")
@Composable
fun ContactRowPreview() {
    ContactRow()
}


private val primaryColor = Color(0xFF3F5A8F)
private val secondaryColor = Color(0xFF5A5F73)
private val buttonTextColor = Color.White

@Composable
fun WaterTracker() {
    var waterCount by remember { mutableIntStateOf(100) }
    var daysInRow by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Трекер воды",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = primaryColor
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "$waterCount мл",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = secondaryColor
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
            onClick = { waterCount += 250 }
        ) {
            Text(
                text = "+250 мл",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = secondaryColor),
            onClick = {
                if (waterCount >= 1500) {
                    daysInRow++
                } else {
                    daysInRow = 0
                }
                waterCount = 0
            }
        ) {
            Text(
                text = "Завершить день",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = buttonTextColor
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Дней подряд с 1500 мл и больше: $daysInRow",
            fontSize = 18.sp,
            color = secondaryColor
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "WaterTracker")
@Composable
fun WaterTrackerPreview() {
    WaterTracker()
}