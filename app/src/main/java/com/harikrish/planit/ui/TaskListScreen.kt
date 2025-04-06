package com.harikrish.planit.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.harikrish.planit.R
import com.harikrish.planit.ui.theme.Indigo500
import com.harikrish.planit.viewmodel.TaskViewModel
import kotlinx.coroutines.launch


@Composable
fun TaskListScreen(viewModel: TaskViewModel = hiltViewModel()) {

    val context = LocalContext.current
    val tasks = viewModel.tasks
    val showDialog = viewModel.showDialog

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.planitnew),
            contentDescription = "background",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(Color.Transparent),
                shape = RectangleShape,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 16.dp, end = 16.dp, bottom = 32.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "Hello Hari",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,

                        )
                    Button(
                        onClick = { viewModel.toggleDialog() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Indigo500
                        ),
                        shape = RoundedCornerShape(8.dp) // Less curved button
                    ) {
                        Text(text = "Add Task")
                    }
                }
            }

            // Show the Dialog when showDialog is true
            if (showDialog) {
                AddTaskDialogScreen(
                    onDismiss = { viewModel.toggleDialog() },
                    onTaskAdded = { taskDescription ->
                        viewModel.addTask(taskDescription)
                        viewModel.toggleDialog()
                    }
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxSize(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(24.dp)
            ) {
                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    items(tasks) { task ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(checked = task.isDone,
                                onCheckedChange = {
                                    viewModel.onTaskDone(task.id)
                                    Log.i("Checked_task", "${task.isDone}")
                                })

                            Text(
                                text = task.description,
                                fontSize = 18.sp,
                                color = Indigo500,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                                    .weight(1f)
                            )
                            if (task.isDone) {
                                IconButton(
                                    onClick = {
                                        viewModel.deleteTask(task.id)
                                        Toast.makeText(context, "Task deleted", Toast.LENGTH_SHORT).show()
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete Task",
                                        tint = Color.Red
                                    )
                                }
                            }
                        }

                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskListScreenPreview() {
    TaskListScreen()
}