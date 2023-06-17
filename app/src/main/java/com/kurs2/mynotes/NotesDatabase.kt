package com.kurs2.mynotes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    companion object{
        private const val DB_NAME: String = "notes2.db"
        private var database: NotesDatabase? = null
        /*Объект синхронзации*/
        private final val LOCK: Any = Any()

        fun getInstance(context: Context): NotesDatabase{
            /*Блок синхронизации исключает создание экземпляров БД из разных потоков
            * в одном потоке создаётся экземпляр БД, другой поток видит, что метод уже занят другим потоком,
            * попадает в замок,
            * ждёт, когда метод освободится другой поток забирает уже созданный ранее экземпляр БД*/
            synchronized(LOCK) {
                if (database == null) {
                    database =
                        Room.databaseBuilder(context, NotesDatabase::class.java, DB_NAME)
                           // .allowMainThreadQueries() /*Удалить*/
                            .build()
                }
            }
            return database!!
        }
    }

    abstract  fun notesDao(): NotesDao
}