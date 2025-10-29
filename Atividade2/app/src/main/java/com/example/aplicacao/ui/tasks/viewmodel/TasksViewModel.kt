package com.example.programacao.ui.tasks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.programacao.ui.tasks.model.Task
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class TasksViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<TasksUiState>(TasksUiState.Loading)

    val uiState: StateFlow<TasksUiState> = _uiState

    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            _uiState.value = TasksUiState.Loading

            delay(1000L)

            val deuErro = Random.nextFloat() < 0.2f

            if (deuErro) {
                _uiState.value = TasksUiState.Error
            } else {
                val mockTasks = listOf(
                    Task(
                        id = 1,
                        title = "Estudar ViewModel e StateFlow",
                        isDone = false
                    ),
                    Task(
                        id = 2,
                        title = "Implementar UIState",
                        isDone = true
                    ),
                    Task(
                        id = 3,
                        title = "Construir a tela com Compose",
                        isDone = false
                    ),
                    Task(
                        id = 4,
                        title = "Testar o fluxo (atualizar/tentar novamente)",
                        isDone = false
                    )
                )

                _uiState.value = TasksUiState.Success(tasks = mockTasks)
            }
        }
    }
}