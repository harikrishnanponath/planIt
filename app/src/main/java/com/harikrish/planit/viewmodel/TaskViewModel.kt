package com.harikrish.planit.viewmodel


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.harikrish.planit.model.Task

class TaskViewModel : ViewModel() {

    val tasks = mutableStateListOf<Task>()

    var showDialog by mutableStateOf(false)

    fun addTask(description: String){
        if (description.isNotBlank()){
            val newTask = Task(tasks.size + 1, description)
            tasks.add(newTask)
        }
    }

    fun toggleDialog(){
        showDialog = !showDialog
    }

    fun onTaskDone(id: Int){
        val index = tasks.indexOfFirst { it.id == id }

        if (index != -1){
            var task = tasks[index]
            tasks[index] = task.copy(isDone = !task.isDone)
            Log.i("ViewModel_Checked", "${task}")
        }

    }
}

