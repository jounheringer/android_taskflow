package com.reringuy.taskflow.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.reringuy.taskflow.data.converters.DateConverter
import com.reringuy.taskflow.data.dao.TaskDao
import com.reringuy.taskflow.data.entities.Task


@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateConverter::class)
abstract class TaskFlowDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}