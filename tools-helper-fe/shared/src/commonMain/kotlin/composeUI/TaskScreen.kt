package composeUI

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import domainTemp.Task

@Composable
fun TaskScreen(task: Task, onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            TaskMainPart(task)
            Button(
                onClick = onClick,
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Text("Назад")
            }
        }
    }
}

@Composable
fun TaskMainPart(task: Task) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "Номер задачи: ${task.id}",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            "Операция : ${task.operation}",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            "Номер операции: ${task.partCount}",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            "Количество деталей: ${task.partCount}",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}