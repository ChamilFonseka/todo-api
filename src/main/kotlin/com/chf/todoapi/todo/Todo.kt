package com.chf.todoapi.todo

import jakarta.persistence.*

@Entity
@Table(name = "todos")
data class Todo(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Int?,

        @Column(nullable = false)
        val name: String,

        @Column(name = "completed")
        val isCompleted: Boolean,
)