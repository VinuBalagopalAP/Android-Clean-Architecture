package com.vinutest.catodoapp.data.datasource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vinutest.catodoapp.domain.model.TodoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [TodoItem::class], version = 1, exportSchema = false)
abstract class TodoDatabase : RoomDatabase(){

    abstract fun todoDao() : TodoDao

    companion object{
        @Volatile
        private var INSTANCE : TodoDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope):TodoDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TodoDatabase::class.java,
                    "todo_table"
                ).addCallback(TodoCallback(scope))
                    .build()

                INSTANCE = instance

                // return instance
                instance
            }
        }
    }

    private class TodoCallback(val scope: CoroutineScope) : Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let {
                    todoDatabase ->
                scope.launch {
                    // if you want to populate database
                    // when RoomDatabase is created
                    // populate here


                }
            }
        }
    }
}
