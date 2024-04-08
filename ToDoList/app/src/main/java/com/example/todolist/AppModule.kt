package com.example.todolist

import com.example.todolist.ui.TodosViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
     viewModel { TodosViewModel() }
}
