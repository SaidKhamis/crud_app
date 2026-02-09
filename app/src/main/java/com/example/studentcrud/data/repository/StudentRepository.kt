package com.example.studentcrud.data.repository

import kotlinx.coroutines.flow.Flow
import com.example.studentcrud.data.entity.Student
import com.example.studentcrud.data.dao.StudentDao

class StudentRepository(private val dao: StudentDao) {
    val allStudents: Flow<List<Student>> = dao.getAllStudents()

    suspend fun insert(student: Student) = dao.insertStudent(student)

    suspend fun update(student: Student) = dao.updateStudent(student)

    suspend fun delete(student: Student) = dao.deleteStudent(student)
}