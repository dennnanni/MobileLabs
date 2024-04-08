package com.example.todolist.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Todo(
    val content: String,
    val isComplete: Boolean = false
)

data class TodosState(val todos: List<Todo>)

interface TodosActions {
    fun addTodo(todo: Todo)
    fun removeTodo(todo: Todo)
    fun toggleComplete(todo: Todo)
}

class TodosViewModel : ViewModel() {
    private val _state = MutableStateFlow(TodosState(listOf(
        Todo("Learn Kotlin", isComplete = true),
        Todo("Learn Jetpack Compose")
    )))
    val state = _state.asStateFlow()

    val actions = object : TodosActions {
        override fun addTodo(todo: Todo) =
            _state.update { TodosState(it.todos + todo) }

        override fun removeTodo(todo: Todo) =
            _state.update { TodosState(it.todos - todo) }

        override fun toggleComplete(todo: Todo) =
            _state.update {
                TodosState(it.todos.map { t ->
                    if (t == todo) t.copy(isComplete = !t.isComplete) else t
                })
            }
    }
}
