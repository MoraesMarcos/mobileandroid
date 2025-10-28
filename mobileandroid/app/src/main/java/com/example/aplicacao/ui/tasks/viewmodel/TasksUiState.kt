package com.example.programacao.ui.tasks.viewmodel

import com.example.programacao.ui.tasks.model.Task

sealed class TasksUiState {
    data class Success(val tasks: List<Task>) : TasksUiState()
    data object Loading : TasksUiState()
    data object Error : TasksUiState()
}
