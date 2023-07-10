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
        val todoOpt = todoRepository.findById(todoId)

        if(!todoOpt.isPresent) throw TodoNotFoundException("Could not find a todo with the id $todoId")

        val todo = todoOpt.get()
        return TodoResponse(todo.id!!, todo.name, todo.isCompleted )
    }

    override fun addTodo(todoRequest: TodoRequest): TodoResponse {
        val savedTodo = todoRepository.save(Todo(null, todoRequest.name, todoRequest.isCompleted))

        return TodoResponse(savedTodo.id!!, savedTodo.name, savedTodo.isCompleted)
    }

    override fun updateTodo(updatedTodoId: Int, updatedTodo: TodoRequest): TodoResponse {
        val todo = todoRepository.getReferenceById(updatedTodoId)
        todo.name = updatedTodo.name
        todo.isCompleted = updatedTodo.isCompleted

        val savedTodo = todoRepository.save(todo)

        return TodoResponse(savedTodo.id!!, savedTodo.name, savedTodo.isCompleted)
    }

    override fun deleteTodo(todoId: Int) {
        TODO("Not yet implemented")
    }
}