package com.harikrish.planit.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int =0,
    val description: String,
    val isDone: Boolean = false
)
