package com.chf.todoapi.todo

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todos")
class TodoController @Autowired constructor(
        private val todoService: TodoService
) {
    @GetMapping
    fun getTodos(): ResponseEntity<Collection<TodoResponse>> {
        return ResponseEntity.ok(todoService.getTodos())
    }

    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoId: Int): ResponseEntity<TodoResponse> {
        return ResponseEntity.ok(todoService.getTodo(todoId))
    }

    @PostMapping
    fun addTodo(@Valid @RequestBody todoRequest: TodoRequest): ResponseEntity<TodoResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(todoService.addTodo(todoRequest))
    }

    @PutMapping("/{todoId}")
    fun updateTodo(@PathVariable todoId: Int, @Valid @RequestBody todoRequest: TodoRequest): ResponseEntity<TodoResponse> {
        return ResponseEntity.ok(todoService.updateTodo(todoId, todoRequest))
    }

    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Int): ResponseEntity<Unit> {
        todoService.deleteTodo(todoId)
        return ResponseEntity.noContent().build()
    }

    @ExceptionHandler(TodoNotFoundException::class)
    fun handleTodoNotFoundException(exception: TodoNotFoundException): ResponseEntity<String> {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.message)
    }
}