package com.reringuy.taskflow

import android.app.Application
import com.reringuy.taskflow.di.modules.databaseModule
import com.reringuy.taskflow.di.modules.taskModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(taskModule, databaseModule)
        }
    }
}