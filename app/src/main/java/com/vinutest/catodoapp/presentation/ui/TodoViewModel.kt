package com.vinutest.catodoapp.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.vinutest.catodoapp.domain.model.TodoItem
import com.vinutest.catodoapp.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val todoRepository: TodoRepository): ViewModel(){

    val allTodoItems : LiveData<MutableList<TodoItem>>
            = todoRepository.getAllTodos.asLiveData()

    fun insert(todoItem: TodoItem) = viewModelScope.launch {
        todoRepository.insert(todoItem)
    }

    fun delete(todoItem: TodoItem) = viewModelScope.launch {
        todoRepository.delete(todoItem)
    }

    fun update(todoItem: TodoItem) = viewModelScope.launch {
        todoRepository.update(todoItem)
    }

    class TodoViewModelFactory(private val todoRepository: TodoRepository)
        : ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TodoViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return TodoViewModel(todoRepository) as T
            }
            throw IllegalArgumentException("Unknown VieModel Class")
        }
        }
}
