package com.reringuy.taskflow.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "tasks",
    indices = [Index(value = ["title"])]
)
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    @ColumnInfo(defaultValue = "false")
    val done: Boolean = false,
    val description: String?,
    val createdAt: Date = Date(),
    val updatedAt: Date = Date(),
    val excluded: Boolean = false,
)
