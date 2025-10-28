package com.example.programacao.ui.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.programacao.ui.tasks.model.Task
import com.example.programacao.ui.tasks.viewmodel.TasksUiState
import com.example.programacao.ui.tasks.viewmodel.TasksViewModel

@Composable
fun TasksScreen(
    viewModel: TasksViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        is TasksUiState.Success -> {
            TasksListScreen(
                tasks = state.tasks,
                onReload = { viewModel.loadTasks() }
            )
        }

        is TasksUiState.Error -> {
            ErrorScreen(
                onRetry = { viewModel.loadTasks() }
            )
        }

        is TasksUiState.Loading -> {
            LoadingScreen()
        }
    }
}

@Composable
fun TasksListScreen(
    tasks: List<Task>,
    onReload: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F6FA)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Minhas atividades",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1A1A1A)
                        )
                    )

                    Text(
                        text = "Acompanhe o andamento das tarefas",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = Color(0xFF6B6B6B)
                        )
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Button(
                    onClick = onReload,
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF394B93),
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
                ) {
                    Text(
                        text = "Atualizar",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                tasks.forEach { task ->
                    TaskRow(task = task)
                }
            }
        }
    }
}

@Composable
fun TaskRow(task: Task) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(12.dp),
                clip = false
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {

        Text(
            text = task.title,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = if (task.isDone) Color(0xFF8A8A8A) else Color(0xFF1A1A1A),
                textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None
            ),
            modifier = Modifier.weight(1f)
        )

        Spacer(modifier = Modifier.width(12.dp))

        val (badgeText, badgeBg, badgeColor) = if (task.isDone) {
            Triple(
                /* texto */ "Concluída",
                /* fundo */ Color(0xFFE6F4EA),
                /* texto */ Color(0xFF227A34)
            )
        } else {
            Triple(
                "Pendente",
                Color(0xFFFFF8E1),
                Color(0xFF8B6C00)
            )
        }

        Box(
            modifier = Modifier
                .background(
                    color = badgeBg,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Text(
                text = badgeText,
                style = MaterialTheme.typography.labelSmall.copy(
                    color = badgeColor,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Composable
fun ErrorScreen(
    onRetry: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F6FA)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Algo deu errado",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFD32F2F)
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Não conseguimos carregar suas tarefas agora.\nVerifique sua conexão e tente de novo.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF4A4A4A),
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onRetry,
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF394B93),
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(
                    horizontal = 20.dp,
                    vertical = 10.dp
                )
            ) {
                Text(
                    text = "Tentar novamente",
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F6FA)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Color(0xFF394B93)
            )
        }
    }
}
