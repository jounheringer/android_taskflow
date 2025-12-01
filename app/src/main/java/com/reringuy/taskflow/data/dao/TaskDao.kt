package com.reringuy.taskflow.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.reringuy.taskflow.data.entities.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks WHERE id = :id")
    suspend fun getTaskById(id: Long): Task
}