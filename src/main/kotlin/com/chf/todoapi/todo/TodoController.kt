package com.chf.todoapi.todo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todos")
class TodoController @Autowired constructor(
        private val todoService: TodoService
) {
    @GetMapping
    fun getTodos(): ResponseEntity<Collection<Todo>> {
        return ResponseEntity.ok(todoService.getTodos())
    }

    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoId: Int): ResponseEntity<Todo> {
        return ResponseEntity.ok(todoService.getTodo())
    }

    @ExceptionHandler(TodoNotFoundException::class)
    fun handleTodoNotFoundException(exception: TodoNotFoundException): ResponseEntity<String> {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.message)
    }
}