package com.example.studentcrud.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studentcrud.data.entity.Student
import com.example.studentcrud.viewmodel.StudentViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.Alignment

@Composable
fun StudentListScreen(viewModel: StudentViewModel, modifier: Modifier = Modifier) {

    val students by viewModel.students.collectAsState()

    var name by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }
    var selectedStudent by remember { mutableStateOf<Student?>(null) }

    var editName by remember { mutableStateOf("") }
    var editCourse by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = course,
            onValueChange = { course = it },
            label = { Text("Course") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (name.isNotBlank() && course.isNotBlank()) {
                    viewModel.addStudent(
                        Student(name = name, course = course)
                    )
                    name = ""
                    course = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Student")
        }

        Spacer(modifier = Modifier.height(16.dp))

          LazyColumn {
            items(students) { student ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "${student.name} - ${student.course}",
                        modifier = Modifier.weight(1f)
                    )

                    Row {
                        IconButton(
                            onClick = {
                                selectedStudent = student
                                editName = student.name
                                editCourse = student.course
                                showDialog = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Update"
                            )
                        }

                        IconButton(
                            onClick = { viewModel.deleteStudent(student) }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete"
                            )
                        }
                    }
                }
            }
        }

        if (showDialog && selectedStudent != null) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(onClick = {
                        viewModel.updateStudent(
                            selectedStudent!!.copy(
                                name = editName,
                                course = editCourse
                            )
                        )
                        showDialog = false
                    }) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                },
                title = { Text("Update Student") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = editName,
                            onValueChange = { editName = it },
                            label = { Text("Name") }
                        )
                        OutlinedTextField(
                            value = editCourse,
                            onValueChange = { editCourse = it },
                            label = { Text("Course") }
                        )
                    }
                }
            )
        }
    }
}
