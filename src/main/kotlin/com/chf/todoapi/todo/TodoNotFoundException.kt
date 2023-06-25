package com.chf.todoapi.todo

class TodoNotFoundException : RuntimeException {
    constructor(message: String) : super(message)
}