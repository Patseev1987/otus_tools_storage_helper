package composeUI

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domainTemp.Order
import domainTemp.OrderStatus

@Composable
fun OrderScreen(orders: List<Order>) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth().weight(1f)) {
            OrdersColumns(orders, OrderStatus.CREATED, "Созданные", Modifier.weight(1f))
            OrdersColumns(orders, OrderStatus.IN_PROGRESS, "Собирают", Modifier.weight(1f))
            OrdersColumns(orders, OrderStatus.COMPLETED, "Готов к выдаче", Modifier.weight(1f))
        }
    }
}

@Composable
fun OrderItem(order: Order) {
    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(10.dp),
        border = BorderStroke(2.dp, Color.Magenta),
        backgroundColor = Order.getColor(order.status),
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "Номер заказа: ${order.id}",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                "Рабочий : ${order.workerSurname}",
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                "Номер операции: ${order.operation}",
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun OrdersColumns(
    orders: List<Order>,
    filter: OrderStatus = OrderStatus.CREATED,
    title: String = "",
    modifier: Modifier = Modifier,
) {
    Column {
        Column(
            modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(title, fontSize = 25.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Monospace)
            Spacer(modifier = Modifier.height(25.dp))
            LazyColumn {
                items(orders.filter { it.status == filter }, key = { it.id }) { order ->
                    OrderItem(order)
                }
            }
        }
    }
}

@Preview
@Composable
fun OrderItemPreview() {
    OrderScreen(Order.ordersExample)
}