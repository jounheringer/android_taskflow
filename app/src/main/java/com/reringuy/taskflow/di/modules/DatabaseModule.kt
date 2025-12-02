package com.reringuy.taskflow.di.modules

import android.app.Application
import androidx.room.Room
import com.reringuy.taskflow.data.TaskFlowDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

fun provideDataBase(application: Application): TaskFlowDatabase =
    Room.databaseBuilder(
        application,
        TaskFlowDatabase::class.java,
        "task_flow_database"
    ).fallbackToDestructiveMigration(false).build()

val databaseModule = module {
    single { provideDataBase(androidApplication()) }
}
