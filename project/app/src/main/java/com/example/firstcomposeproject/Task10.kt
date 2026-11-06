package com.example.firstcomposeproject

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

data class Product(
    val id: Int,
    val name: String,
    val price: Int
)

@Composable
fun ShoppingCartScreen() {
    val context = LocalContext.current

    var products by remember {
        mutableStateOf(
            listOf(
                Product(0, "Товар #1", 100),
                Product(1, "Товар #2", 150),
                Product(2, "Товар #3", 56)
            )
        )
    }

    // Сумма пересчитывается из списка, а не накапливается между перерисовками
    val totalSum = products.sumOf { it.price }

    Column(modifier = Modifier.padding(16.dp)) {
        products.forEach { product ->
            Text(text = "${product.name} - ${product.price} рублей")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Товаров на сумму: $totalSum рублей")

        AddProductSection(
            onAdd = {
                val newProduct = Product(
                    id = products.size,
                    name = "Товар #${products.size + 1}",
                    price = Random.nextInt(1, 101)
                )
                products = products + newProduct

                // Toast показываем здесь, в обработчике нажатия, а не в композиции
                if (totalSum + newProduct.price > 500) {
                    Toast.makeText(context, "Доставка бесплатная!", Toast.LENGTH_SHORT).show()
                }
            }
        )

        RemoveProductSection(
            isVisible = products.isNotEmpty(),
            onRemove = {
                products = products.dropLast(1)
            }
        )
    }
}

@Composable
fun AddProductSection(
    onAdd: () -> Unit
) {
    Button(onClick = onAdd) {
        Text(text = "Добавить товар")
    }
}

@Composable
fun RemoveProductSection(
    isVisible: Boolean,
    onRemove: () -> Unit
) {
    if (isVisible) {
        Button(onClick = onRemove) {
            Text(text = "Удалить товар")
        }
    }
}

@Preview(showSystemUi = true, name = "ShoppingCart")
@Composable
fun ShoppingCartScreenPreview() {
    ShoppingCartScreen()
}