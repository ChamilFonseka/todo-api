package com.chf.todoapi.todo

interface TodoService {
    fun getTodos(): Collection<TodoResponse>
    fun getTodo(): TodoResponse
}