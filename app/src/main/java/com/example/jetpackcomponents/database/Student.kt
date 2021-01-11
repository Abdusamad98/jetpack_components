package com.example.jetpackcomponents.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val contactId: Long = 0L,
    val name: String,
    val number: String
)