package com.example.studentcrud.ui.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.room.Delete
import com.example.studentcrud.data.entity.Student

@Composable
fun StudentItem(
    student: Student,
    onEditClick: (Student) -> Unit,
    onDeleteClick: (Student) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = student.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = student.course,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        IconButton(
            onClick = {onEditClick(student)}
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit"
            )
        }

        IconButton(
            onClick = {onDeleteClick(student)}
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete"
            )
        }
    }
}