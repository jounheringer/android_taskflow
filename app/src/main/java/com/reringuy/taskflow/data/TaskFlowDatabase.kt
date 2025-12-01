package com.reringuy.taskflow.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.reringuy.taskflow.data.dao.TaskDao
import com.reringuy.taskflow.data.entities.Task


@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = true
)
abstract class TaskFlowDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}