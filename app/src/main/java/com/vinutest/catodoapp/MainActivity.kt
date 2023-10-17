package com.vinutest.catodoapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.vinutest.catodoapp.data.datasource.database.TodoDao
import com.vinutest.catodoapp.domain.model.TodoItem
import com.vinutest.catodoapp.presentation.di.NetworkModule
import com.vinutest.catodoapp.presentation.ui.TodoViewModel
import com.vinutest.catodoapp.ui.theme.CATodoAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val todoViewModel: TodoViewModel by viewModels {
        TodoViewModel.TodoViewModelFactory((application as MyApplication).repository)
    }

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
                    val response = NetworkModule.provideDemoApiService()

                    GlobalScope.launch {
                        val result = response.getTodos()
                        Log.d("TAG", "onCreate: ${result.body()?.first()}")
                    }
                }
            }
        }
    }
}

@Composable
fun TodoList(todos: List<TodoItem>) {
    LazyColumn {
        items(todos) { todoItem ->
            Log.d("Item Name", "TodoList: ${todoItem.title}")
            Text("${todoItem.title}")
        }
    }
}