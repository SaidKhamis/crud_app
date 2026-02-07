package com.example.studentcrud.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentcrud.data.repository.StudentRepository
import com.example.studentcrud.data.entity.Student
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow

class StudentViewModel (private val repository: StudentRepository): ViewModel() {
    private val _students = MutableStateFlow <List<Student>> (emptyList())

    val students: StateFlow<List<Student>> = _students

    init { //Update the UI as soon as the Data in the Database Change.
        viewModelScope.launch {
            repository.allStudents.collect {
                studentList -> _students.value = studentList
            }
        }
    }

    //A function to call the Insert Query from StudentDao
    fun addStudent(student: Student) = viewModelScope.launch {
        repository.insert(student)
    }

    fun updateStudent (student: Student) = viewModelScope.launch {
        repository.update(student)
    }

    fun deleteStudent (student: Student) = viewModelScope.launch {
        repository.delete(student)
    }
}
