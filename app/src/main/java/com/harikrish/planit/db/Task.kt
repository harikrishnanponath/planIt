package com.harikrish.planit.db

data class Task(
    val id: Int,
    val description: String,
    var isDone: Boolean = false
)
