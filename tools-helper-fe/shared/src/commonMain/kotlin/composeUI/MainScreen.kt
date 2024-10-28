package composeUI

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import domainTemp.Order
import domainTemp.Task


const val HOME = 1
const val ORDERS = 2
const val TASK = 3

var task = Task()

@Composable
fun MainScreen() {

    var state by remember { mutableStateOf(HOME) }
    Column(Modifier.fillMaxSize()) {
        Box(Modifier.weight(1f)) {
            when (state) {
                HOME -> {
                    WorkerScreen {
                        state = TASK
                        task = it
                    }
                }

                ORDERS -> {
                    OrderScreen(Order.ordersExample)
                }

                TASK -> {
                    TaskScreen(task) {
                        state = HOME
                    }
                }
            }
        }


        BottomAppBar(
            backgroundColor = Color.White,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp)
        ) {
            BottomNavigationItem(
                selected = state == HOME,
                onClick = {
                    state = HOME
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.Home, contentDescription = "Home")
                    }
                }
            )
            BottomNavigationItem(
                selected = state == ORDERS,
                onClick = {
                    state = ORDERS
                },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Orders")
                    }
                }
            )
        }
    }
}
