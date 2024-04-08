package com.example.todolist.data.repositories

import com.example.todolist.data.database.Todo
import com.example.todolist.data.database.TodosDAO
import kotlinx.coroutines.flow.Flow

class TodosRepository(private val todosDAO: TodosDAO) {
    val todos: Flow<List<Todo>> = todosDAO.getAll()
    suspend fun upsert(todo: Todo) = todosDAO.upsert(todo)
    suspend fun delete(todo: Todo) = todosDAO.delete(todo)
}