package com.harikrish.planit.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harikrish.planit.db.Task
import com.harikrish.planit.db.TaskDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val taskDao: TaskDao
) : ViewModel() {

    val tasks = mutableStateListOf<Task>()

    var showDialog by mutableStateOf(false)

    init {
        loadTasks()
    }

    private fun loadTasks() {
        viewModelScope.launch {
            viewModelScope.launch {
                tasks.clear()
                tasks.addAll(taskDao.getAllTasks())
            }

        }
    }

    fun addTask(description: String) {
        if (description.isNotBlank()) {
            viewModelScope.launch {
                val newTask = Task(description = description)
                taskDao.insertTask(newTask)
                tasks.add(newTask)
                loadTasks()
            }

        }
    }

    fun deleteTask(id: Int) {
        viewModelScope.launch {
            val index = tasks.indexOfFirst { it.id == id }
            if (index != -1) {
                var task = tasks[index]
                tasks.remove(task)
                taskDao.deleteTaskById(task.id)
            }
        }
    }

    fun toggleDialog() {
        showDialog = !showDialog
    }

    fun onTaskDone(id: Int) {
        viewModelScope.launch {
            val index = tasks.indexOfFirst { it.id == id }
            if (index != -1) {
                var updateTask = tasks[index]
                tasks[index] = updateTask.copy(isDone = !updateTask.isDone)
                taskDao.updateTask(tasks[index])
                Log.i("CHECKEDBOX", "$updateTask")

            }
        }
    }

}

