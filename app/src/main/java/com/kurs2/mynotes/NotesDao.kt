package com.kurs2.mynotes

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NotesDao {
    /*Метод получает все заметки из БД
    * Вызывается при запросе к БД*/
    @Query("SELECT * FROM notes ORDER BY dayOfWeek ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert
    fun insertNote(note: Note): Unit
    @Delete
    fun deleteNote(note: Note): Unit

    @Query("DELETE FROM notes")
    fun deleteAllNotes(): Unit

}