package com.vinutest.catodoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.vinutest.catodoapp.data.datasource.database.TodoDao
import com.vinutest.catodoapp.presentation.di.NetworkModule
import com.vinutest.catodoapp.presentation.ui.TodoViewModel
import com.vinutest.catodoapp.ui.theme.CATodoAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val todoViewModel: TodoViewModel by viewModels()

    lateinit var todoDao: TodoDao

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
                    Log.d("TAG", "onCreate: ")
                    val retrofitResponse = NetworkModule.provideAPICallService()

                    GlobalScope.launch(Dispatchers.IO) {
                        val result = retrofitResponse.getTodos()
//                        Log.d("TAG", "onCreate: ${result.body()?.first()}")


                        val todoItems = result.body()!!

                        var inserted = 1
                        for (todoItem in todoItems) {
                            if (inserted <= 5) {
                                todoItem.id = todoItem.id?.plus(1)
                                todoViewModel.insert(todoItem)
                                inserted+=1
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//fun TodoList(todos: List<TodoItem>) {
//    LazyColumn {
//        items(todos) { todoItem ->
//            Log.d("Item Name", "TodoList: ${todoItem.title}")
//            Text("${todoItem.title}")
//        }
//    }
//}