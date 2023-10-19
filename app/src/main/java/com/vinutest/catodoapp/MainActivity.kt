@file:OptIn(DelicateCoroutinesApi::class)

package com.vinutest.catodoapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.vinutest.catodoapp.data.datasource.database.TodoDao
import com.vinutest.catodoapp.domain.model.TodoItem
import com.vinutest.catodoapp.presentation.di.NetworkModule
import com.vinutest.catodoapp.presentation.ui.TodoScreen
import com.vinutest.catodoapp.presentation.ui.TodoViewModel
import com.vinutest.catodoapp.ui.theme.CATodoAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val todoViewModel: TodoViewModel by viewModels()

    lateinit var todoDao: TodoDao

    lateinit var todoItem: MutableList<TodoItem>

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CATodoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val retrofitResponse = NetworkModule.provideAPICallService()

//                    if(!todoViewModel.allTodoItems.isInitialized ){
                        GlobalScope.launch(Dispatchers.IO) {
                            val result = retrofitResponse.getTodos()
                            val todoItems = result.body()!!
                            var inserted = 1

                            todoItems.forEach { todoItem ->
                                if (inserted <= 5) {
                                    todoViewModel.insert(todoItem)
                                    inserted += 1
                                }
                            }
                        }
//                    }
                    TodoApp(todoViewModel= todoViewModel)
                }
            }
        }
    }
}

@Composable
fun TodoApp(todoViewModel: TodoViewModel) {

    val noteList = todoViewModel.todoList.collectAsState().value

    TodoScreen(
        todo = noteList,
        onRemoveNote = { todoViewModel.delete(it) },
        onAddNote = { todoViewModel.insert(it) })
}