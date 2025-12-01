package com.reringuy.taskflow.di.modules

import android.app.Application
import androidx.room.Room
import com.reringuy.taskflow.data.TaskFlowDatabase
import com.reringuy.taskflow.data.dao.TaskDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

fun provideDataBase(application: Application): TaskFlowDatabase =
    Room.databaseBuilder(
        application,
        TaskFlowDatabase::class.java,
        "task_flow_database"
    ).fallbackToDestructiveMigration(false).build()

fun provideTaskDao(taskFlowDatabase: TaskFlowDatabase): TaskDao = taskFlowDatabase.taskDao()

val databaseModule = module {
    single { provideDataBase(androidApplication()) }
    single { provideTaskDao(get()) }
}
