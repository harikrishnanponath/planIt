package com.harikrish.planit.model

data class Task(
    val id: Int,
    val description: String,
    var isDone: Boolean = false
)
