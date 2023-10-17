package com.vinutest.catodoapp.domain.repository

import androidx.annotation.WorkerThread
import com.vinutest.catodoapp.data.datasource.database.TodoDao
import com.vinutest.catodoapp.domain.model.TodoItem
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityRetainedScoped
class TodoRepository @Inject constructor(private val todoDao: TodoDao){

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(todoItem: TodoItem){
        CoroutineScope(Dispatchers.IO).launch {
            todoDao.insertTodo(todoItem)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(todoItem: TodoItem){
        CoroutineScope(Dispatchers.IO).launch {
            todoDao.deleteTodo(todoItem)
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(todoItem: TodoItem){
        CoroutineScope(Dispatchers.IO).launch {
            todoDao.updateTodo(todoItem)
        }
    }

    val getAllTodos: Flow<MutableList<TodoItem>> = todoDao.getAllTodos()
}