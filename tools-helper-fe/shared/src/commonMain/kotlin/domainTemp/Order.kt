package domainTemp

import androidx.compose.ui.graphics.Color

data class Order(
    val id: Long = 0,
    val workerSurname: String = "",
    val operation: String = "",
    val status: OrderStatus = OrderStatus.CREATED
) {

    companion object {

        fun getColor(status: OrderStatus) = when (status) {
            OrderStatus.CREATED -> Color.LightGray
            OrderStatus.IN_PROGRESS -> Color.Yellow
            OrderStatus.COMPLETED -> Color.Green
            OrderStatus.FINISHED -> Color.Cyan
        }

        val ordersExample = listOf(
            Order(
                id = 1,
                workerSurname = "Иванов",
                operation = "Токарная 45",
                status = OrderStatus.IN_PROGRESS
            ),
            Order(
                id = 2,
                workerSurname = "Сергеев",
                operation = "Фрезерная 60",
                status = OrderStatus.COMPLETED
            ),
            Order(
                id = 3,
                workerSurname = "Козлов",
                operation = "Шлифовальная 20",
                status = OrderStatus.FINISHED
            ),
            Order(
                id = 4,
                workerSurname = "Дулин",
                operation = "Токарная 100",
            )
        )
    }
}

enum class OrderStatus {
    CREATED, IN_PROGRESS, COMPLETED, FINISHED
}
