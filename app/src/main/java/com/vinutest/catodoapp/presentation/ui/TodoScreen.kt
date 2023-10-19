package com.vinutest.catodoapp.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.vinutest.catodoapp.R
import com.vinutest.catodoapp.domain.model.TodoItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    todo: List<TodoItem>,
    onAddTodo: (TodoItem) -> Unit,
    onRemoveTodo: (TodoItem) -> Unit,
    onUpdateTodo: (TodoItem) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    fontWeight = FontWeight.Medium,
                )
                    },
            actions = {},
            navigationIcon ={},
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = Color.LightGray
            )
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TodoInputText(
                modifier= Modifier.padding(top= 9.dp, bottom = 8.dp),
                text = title,
                label = "Add todo",
                onTextChange = {
                    if (it.all { char -> char.isLetter() || char.isWhitespace() })
                        title = it
                }
            )
            SaveButton(
                modifier= Modifier.padding(top= 9.dp, bottom = 8.dp),
                text = "Save",
                onClick = {
                    if (title.isNotEmpty() ){
                        onAddTodo(TodoItem(title = title))
                        title = ""
                        Toast.makeText(context, "Todo Added", Toast.LENGTH_SHORT).show()
                    }
                })
            Divider(modifier = Modifier.padding(start = 12.dp, end = 12.dp))
            LazyColumn(modifier = Modifier.padding(top = 12.dp)){
                items(todo) { todo ->
                    TodoRow(
                        todo = todo,
                        onNoteClicked = {
                            onRemoveTodo(todo)
                            Toast.makeText(context, "Todo Removed", Toast.LENGTH_SHORT).show()
                        },
                        onEditTitle = {
                            onUpdateTodo(todo)
                            Toast.makeText(context, "Todo Updated", Toast.LENGTH_SHORT).show()
                        })
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoRow(
    modifier: Modifier = Modifier,
    todo: TodoItem,
    onNoteClicked: (TodoItem) -> Unit,
    onEditTitle: (TodoItem) -> Unit
) {
    var title by remember {
        mutableStateOf(todo.title)
    }

    var updateTitle by remember {
        mutableStateOf(false)
    }

    Surface(
        modifier = modifier
            .padding(4.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp,
                    bottomStart = 10.dp,
                    bottomEnd = 10.dp
                )
            )
            .fillMaxWidth(),
        color = Color.LightGray,
        shadowElevation = 6.dp,
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    onNoteClicked(todo)
                }
                .padding(horizontal = 14.dp, vertical = 8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (updateTitle) {
                    AlertDialog(
                        onDismissRequest = {
                            updateTitle = false
                        },
                        title = {
                            Text("Edit Title")
                        },
                        text = {
                            TextField(
                                value = title!!,
                                onValueChange = {
                                    title = it
                                }
                            )
                        },
                        confirmButton = {
                            TextButton(onClick = {
                                onEditTitle(todo.copy(title = title))
                                updateTitle = false
                            }) {
                                Text("Update")
                            }
                        }
                    )
                } else {
                    Text(
                        text = title!!,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(2f)
                    )
                }
                IconButton(onClick = { updateTitle = true }) {
                    Icon(
                        Icons.Default.Edit,
                        "edit title"
                    )
                }
            }
            }
        }
}