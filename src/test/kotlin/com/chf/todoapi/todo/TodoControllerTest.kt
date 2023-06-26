package com.chf.todoapi.todo

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@WebMvcTest
class TodoControllerTest @Autowired constructor(
        private val mockMvc: MockMvc,
        private val objectMapper: ObjectMapper
) {
    private val baseUrl = "/api/todos"
    private val todos = listOf(
            TodoResponse(1, "Grocery shopping", true),
            TodoResponse(2, "Cook dinner", false),
            TodoResponse(3, "Evening exercise", false),
    )

    @MockBean
    lateinit var todoService: TodoService

    @Test
    fun `should return all todos`() {


        `when`(todoService.getTodos())
                .thenReturn(todos)

        mockMvc.get(baseUrl)
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.length()") { value(todos.size) }
                    jsonPath("$[0].id") { value(todos[0].id) }
                    jsonPath("$[0].name") { value(todos[0].name) }
                    jsonPath("$[0].isCompleted") { value(todos[0].isCompleted) }
                    jsonPath("$[1].id") { value(todos[1].id) }
                    jsonPath("$[1].name") { value(todos[1].name) }
                    jsonPath("$[1].isCompleted") { value(todos[1].isCompleted) }
                    jsonPath("$[2].id") { value(todos[2].id) }
                    jsonPath("$[2].name") { value(todos[2].name) }
                    jsonPath("$[2].isCompleted") { value(todos[2].isCompleted) }
                }
    }

    @Test
    fun `should return empty payload when there are no todos`() {
        `when`(todoService.getTodos())
                .thenReturn(listOf())

        mockMvc.get(baseUrl)
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$") { isEmpty() }
                }
    }

    @Test
    fun `should return an existing todo return`() {
        val todoId = 1

        `when`(todoService.getTodo(todoId))
                .thenReturn(todos.first { it.id == todoId })

        mockMvc.get("$baseUrl/$todoId")
                .andExpect {
                    status { isOk() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.id") { value(todos[0].id) }
                    jsonPath("$.name") { value(todos[0].name) }
                    jsonPath("$.isCompleted") { value(todos[0].isCompleted) }
                }
    }

    @Test
    fun `should return 404 when todo id is not exists`() {
        val todoId = -99
        val exceptionMessage = "Could not find a todo with the id $todoId"

        `when`(todoService.getTodo(todoId))
                .thenThrow(TodoNotFoundException(exceptionMessage))

        mockMvc.get("$baseUrl/$todoId")
                .andExpect {
                    status { isNotFound() }
                    content { string(exceptionMessage) }
                }
    }

    @Test
    fun `should create a new todo`() {
        val todoRequest = TodoRequest("Study Kotlin", false)
        val newTodoId = 4
        val todoResponse = TodoResponse(newTodoId, todoRequest.name, todoRequest.isCompleted)

        `when`(todoService.addTodo(todoRequest))
                .thenReturn(todoResponse)

        mockMvc.post(baseUrl) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(todoRequest)
        }.andExpect {
            status { isCreated() }
            content {
                contentType(MediaType.APPLICATION_JSON)
                jsonPath("$.id") { value(newTodoId) }
                jsonPath("$.name") { value(todoRequest.name) }
                jsonPath("$.isCompleted") { value(todoRequest.isCompleted) }
            }
        }
    }
}