package com.chf.todoapi.todo

interface TodoService {
    fun getTodos(): Collection<TodoResponse>
    fun getTodo(todoId: Int): TodoResponse
    fun addTodo(todoRequest: TodoRequest): TodoResponse
}