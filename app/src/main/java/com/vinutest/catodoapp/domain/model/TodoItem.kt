package com.vinutest.catodoapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_table")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String?,
)