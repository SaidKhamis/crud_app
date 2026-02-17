package com.example.studentcrud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.studentcrud.data.database.AppDatabase
import com.example.studentcrud.data.repository.StudentRepository
import com.example.studentcrud.ui.screens.StudentListScreen
import com.example.studentcrud.ui.theme.StudentCrudTheme
import com.example.studentcrud.viewmodel.StudentViewModel
import com.example.studentcrud.viewmodel.StudentViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database by lazy { AppDatabase.getDatabase(this) }
        val repository by lazy { StudentRepository(database.studentDao()) }
        val viewModel: StudentViewModel by viewModels {
            StudentViewModelFactory(repository)
        }

        enableEdgeToEdge()
        setContent {
            StudentCrudTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StudentListScreen(
                        viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}