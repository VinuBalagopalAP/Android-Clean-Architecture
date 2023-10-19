package com.vinutest.catodoapp.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vinutest.catodoapp.R
import com.vinutest.catodoapp.domain.model.TodoItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    todo: List<TodoItem>,
    onAddNote: (TodoItem) -> Unit,
    onRemoveNote: (TodoItem) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name))},
            actions = {
                Icon(imageVector = Icons.Rounded.Notifications, contentDescription = "Icon")
            },
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
                label = "Hello",
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
                        onAddNote(TodoItem(title = title))
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
                            onRemoveNote(todo)
                            Toast.makeText(context, "Note Removed", Toast.LENGTH_SHORT).show()
                        })
                }
            }
        }
    }
}


@Composable
fun TodoRow(
    modifier: Modifier = Modifier,
    todo: TodoItem,
    onNoteClicked: (TodoItem) -> Unit
) {
    Surface(
        modifier = modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color.LightGray,
        shadowElevation = 6.dp,
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    onNoteClicked(todo)
                }
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = todo.title!!,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
            }

//            Text(
//                text = note.entryDate.format(DateTimeFormatter.ofPattern("EEE, d MMM" )),
//                style = MaterialTheme.typography.labelMedium
//                )
        }
}




