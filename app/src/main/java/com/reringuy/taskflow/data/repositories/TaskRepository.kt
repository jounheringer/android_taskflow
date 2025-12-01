package com.reringuy.taskflow.data.repositories

import com.reringuy.taskflow.data.dao.TaskDao
import com.reringuy.taskflow.data.entities.Task

interface TaskRepository {
    fun getTaskById(id: Long): Task?
    suspend fun addTask(task: Task): Long
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(task: Task)
}

class TaskRepositoryImpl(
    private val taskDao: TaskDao
) : TaskRepository {
    override fun getTaskById(id: Long): Task? {
        return taskDao.getTaskById(id)
    }

    override suspend fun addTask(task: Task): Long {
        return taskDao.addTask(task)
    }

    override suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
}