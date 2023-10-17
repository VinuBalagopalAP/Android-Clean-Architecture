package com.vinutest.catodoapp.data.datasource.api

import com.vinutest.catodoapp.domain.model.TodoItem
import retrofit2.Response
import retrofit2.http.GET

interface APICall {
    @GET("todos")
    suspend fun getTodos(): Response<List<TodoItem>>
}