package com.chf.todoapi.todo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl @Autowired constructor(
        private val todoRepository: TodoRepository
) : TodoService {
    override fun getTodos(): Collection<TodoResponse> {
        TODO("Not yet implemented")
    }

    override fun getTodo(todoId: Int): TodoResponse {
        TODO("Not yet implemented")
    }

    override fun addTodo(todoRequest: TodoRequest): TodoResponse {
        val todo = mapTo(todoRequest)
        val savedTodo = todoRepository.save(todo)
        return mapTo(savedTodo)
    }

    override fun updateTodo(updatedTodoId: Int, updatedTodo: TodoRequest): TodoResponse {
        TODO("Not yet implemented")
    }

    override fun deleteTodo(todoId: Int) {
        TODO("Not yet implemented")
    }

    private fun mapTo(todoRequest: TodoRequest): Todo {
        return Todo(null, name = todoRequest.name, isCompleted = todoRequest.isCompleted)
    }

    private fun mapTo(todo: Todo): TodoResponse {
        return TodoResponse(todo.id!!, todo.name, todo.isCompleted)
    }
}