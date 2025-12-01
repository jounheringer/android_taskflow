package com.reringuy.taskflow.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.reringuy.taskflow.data.entities.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks WHERE id = :id")
    fun getTaskById(id: Long): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task): Long

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)
}