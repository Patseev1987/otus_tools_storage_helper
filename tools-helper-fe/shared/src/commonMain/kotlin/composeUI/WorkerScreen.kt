package composeUI

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domainTemp.EmployeeScreenModel
import domainTemp.Task



@Composable
fun WorkerScreen(onClick: (Task) -> Unit = {}) {

    val employeeScreenModel = EmployeeScreenModel(
        avatar = "",
        name = "Иван",
        surname = "Сергеев",
        department = "Цех №19",
        shiftTasks = Task.getExampleTasks()
    )
    Box(modifier = Modifier.fillMaxSize().padding(8.dp).background(Brush.linearGradient(
        listOf(Color.Blue.copy(alpha = 0.1f),Color.Gray.copy(alpha = 0.7f))
    ))) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.padding(8.dp)) {
                Image(
                    painter = ColorPainter(Color.Red),
                    contentDescription = "avatar",
                    modifier = Modifier.size(150.dp)
                )
                Column(Modifier.padding(8.dp).weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Имя : ${employeeScreenModel.name}", fontSize = 18.sp)
                    Spacer(Modifier.padding(8.dp))
                    Text("Фамилия : ${employeeScreenModel.surname}", fontSize = 18.sp)
                    Spacer(Modifier.padding(8.dp))
                    Text("Подразделение : ${employeeScreenModel.department}", fontSize = 18.sp)
                }
            }
            Spacer(Modifier.height(8.dp))
            Text("Сменное задание:", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Column {
                Card(
                    modifier = Modifier.fillMaxWidth()
                        .padding(8.dp),
                    border = BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color.LightGray
                ) {
                    LazyColumn {
                        items(items = employeeScreenModel.shiftTasks) { task ->
                            Card(
                                border = BorderStroke(1.dp, Color.Black),
                                modifier = Modifier.padding(8.dp).clickable { onClick(task) }) {
                                Column(
                                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row(
                                        modifier = Modifier.padding(8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text("№${task.id}", fontSize = 18.sp)
                                        Spacer(Modifier.width(40.dp))
                                        Text("Операция: ${task.operation}")
                                    }
                                    Text("Количество деталей: ${task.partCount}")
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
@Preview
fun WorkerScreenPreview() {
    WorkerScreen(onClick = {})
}