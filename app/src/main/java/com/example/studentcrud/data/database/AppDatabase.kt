package com.example.studentcrud.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.studentcrud.data.entity.Student
import com.example.studentcrud.data.dao.StudentDao

@Database(
    entities = [Student::class],
    exportSchema = false,
    version = 2
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun studentDao(): StudentDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "student_db"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}