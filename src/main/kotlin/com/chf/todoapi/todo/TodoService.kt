package com.chf.todoapi.todo

interface TodoService {
    fun getTodos(): Collection<Todo>
    fun getTodo(): Todo
}