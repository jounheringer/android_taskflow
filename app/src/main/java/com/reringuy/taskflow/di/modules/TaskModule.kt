package com.reringuy.taskflow.di.modules

import com.reringuy.taskflow.data.repositories.TaskRepository
import com.reringuy.taskflow.data.repositories.TaskRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val taskModule = module {
    singleOf(::TaskRepositoryImpl) { bind<TaskRepository>() }
}