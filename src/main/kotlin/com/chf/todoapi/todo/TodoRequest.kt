package com.chf.todoapi.todo

import jakarta.validation.constraints.NotBlank

data class TodoRequest(@NotBlank val name: String, val isCompleted: Boolean)
