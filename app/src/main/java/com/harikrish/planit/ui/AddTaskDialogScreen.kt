package com.harikrish.planit.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.harikrish.planit.ui.theme.Indigo300
import com.harikrish.planit.ui.theme.Indigo500

@Composable
fun AddTaskDialogScreen(
    onDismiss: () -> Unit,
    onTaskAdded: (String) -> Unit
) {
    var taskInput by remember { mutableStateOf(TextFieldValue("")) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White)
        ) {
            Column (
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "New Task",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Indigo500,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Task Input Field
                OutlinedTextField(
                    value = taskInput,
                    onValueChange = { taskInput = it },
                    label = { },
                    colors = TextFieldDefaults.colors(
                        unfocusedLabelColor = Color.Gray,
                        focusedLabelColor = Indigo500
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Buttons Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))

                    Button(
                        onClick = {
                            onTaskAdded(taskInput.text)
                            taskInput = TextFieldValue("") // Clear input field
                        }, shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Save Task")
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun DialogPreview(){
    AddTaskDialogScreen(onDismiss = {}) { }
}