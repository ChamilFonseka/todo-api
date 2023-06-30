package com.chf.todoapi.todo

import jakarta.persistence.*

@Entity
@Table(name = "todos")
data class Todo(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private val id: Long,

        @Column(nullable = false)
        private  val name: String,

        @Column(name = "completed")
        private val isCompleted: Boolean,
)