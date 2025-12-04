package com.reringuy.taskflow.di.modules

import com.reringuy.taskflow.data.TaskFlowDatabase
import com.reringuy.taskflow.data.repositories.TaskRepository
import com.reringuy.taskflow.ui.addtask.AddTaskViewModel
import com.reringuy.taskflow.ui.tasklist.TaskListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val taskModule = module {
    single { get<TaskFlowDatabase>().taskDao() }
    singleOf(::TaskRepository)
    singleOf(::AddTaskViewModel)
    viewModelOf(::TaskListViewModel)
}