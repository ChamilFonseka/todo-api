package com.chf.todoapi.todo

interface TodoService {
    fun getTodos(): Collection<TodoResponse>
    fun getTodo(todoId: Int): TodoResponse
    fun addTodo(todoRequest: TodoRequest): TodoResponse
    fun updateTodo(updatedTodoId: Int, updatedTodo: TodoRequest): TodoResponse
    fun deleteTodo(todoId: Int)
}