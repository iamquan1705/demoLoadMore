package com.example.loadmoreexam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Exam_Table")
data class Exam(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String
)
