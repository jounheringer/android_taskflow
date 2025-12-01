package com.reringuy.taskflow.data.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "tasks",
    indices = [Index(value = ["title"])]
)
data class Task(
    @PrimaryKey
    val id: Long,
    val title: String,
    val description: String,
    val createdAt: Date,
    val updatedAt: Date,
    val excluded: Boolean,
)
