package com.vinutest.catodoapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "todo_table")
data class TodoItem(
    @PrimaryKey(autoGenerate = false)
    var id: Int? = null,
    val title: String?,
)