package com.example.studentcrud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.studentcrud.data.database.AppDatabase
import com.example.studentcrud.data.repository.StudentRepository
import com.example.studentcrud.ui.screens.StudentListScreen
import com.example.studentcrud.viewmodel.StudentViewModel
import com.example.studentcrud.viewmodel.StudentViewModelFactory

class MainActivity : ComponentActivity() {
    override fun  onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Room Setup
        val dao = AppDatabase.getDatabase(this).studentDao()
        val repository = StudentRepository (dao)
        val factory = StudentViewModelFactory(repository)

        setContent {
            val studentViewModel: StudentViewModel =
                viewModel(factory = factory)

            StudentListScreen(viewModel = studentViewModel)
        }
    }
}
