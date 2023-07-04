package com.chf.todoapi.todo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TodoServiceImplTest  @Autowired constructor(
        private val todoRepository: TodoRepository
) {

    private lateinit var todoService: TodoService

    @BeforeAll
    fun setup() {
        todoService = TodoServiceImpl(todoRepository)
    }

    @Test
    fun getTodos() {

    }

    @Test
    fun getTodo() {
    }

    @Test
    fun `should add the todoRequest and return a todoResponse with a generated id`() {
        val todoRequest = TodoRequest("Go to shopping", false)

        val todoResponse = todoService.addTodo(todoRequest)

        assertThat(todoResponse).isNotNull
        assertThat(todoResponse.id).isNotNull
        assertThat(todoResponse.name).isEqualTo(todoRequest.name)
        assertThat(todoResponse.isCompleted).isEqualTo(todoRequest.isCompleted)
    }

    @Test
    fun updateTodo() {
    }

    @Test
    fun deleteTodo() {
    }
}