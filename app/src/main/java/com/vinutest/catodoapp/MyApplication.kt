package com.vinutest.catodoapp

import android.app.Application
import com.vinutest.catodoapp.data.datasource.database.TodoDatabase
import com.vinutest.catodoapp.domain.repository.TodoRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class MyApplication : Application(){

//    // No need to cancel this scope as it'll be torn down with the process
//
//    private val applicationScope = CoroutineScope(SupervisorJob())
//
//    // Using by lazy so the database and the repository are only created when they're needed
//    // rather than when the application starts
//    private val database by lazy {
//        TodoDatabase.getDatabase(this,applicationScope)
//    }
//
//    val repository by lazy {
//        TodoRepository(database.todoDao())
//    }
}