package com.example.todolist.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1)
abstract class TodoListDatabase : RoomDatabase() {
    abstract fun todosDAO(): TodosDAO

    companion object {
        @Volatile
        private var instance: TodoListDatabase? = null
        fun getDatabase(ctx: Context) =
            instance ?:
            synchronized(this) {
                instance = Room.databaseBuilder(
                    ctx,
                    TodoListDatabase::class.
                    java,
                    "todo-list"
                ).build()
                instance
            }
    }
}