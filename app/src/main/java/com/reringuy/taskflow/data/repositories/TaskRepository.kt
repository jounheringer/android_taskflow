package com.reringuy.taskflow.data.repositories

import com.reringuy.taskflow.data.dao.TaskDao
import com.reringuy.taskflow.data.entities.Task


class TaskRepository(private val taskDao: TaskDao) {
    fun getAll(): List<Task> = taskDao.getAll()

    fun getTaskById(id: Long) = taskDao.getTaskById(id)

    suspend fun addTask(task: Task) = taskDao.addTask(task)

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }
}