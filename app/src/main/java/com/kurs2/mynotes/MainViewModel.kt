package com.kurs2.mynotes

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MainViewModel constructor(application: Application) : AndroidViewModel(application) {
    private lateinit var _notes: LiveData<List<Note>>
   // val notes: LiveData<List<Note>>
   //     get() = _notes

    companion object{
        private lateinit var database: NotesDatabase

    }

    init {
        database = NotesDatabase.getInstance(getApplication())
        _notes = database.notesDao().getAllNotes()
    }

    fun getNotes(): LiveData<List<Note>>{
        return _notes
    }

    fun insertNote(note: Note){
        InsertTask().execute(note)
    }

    fun deleteNote(note: Note){
        DeleteTask().execute(note)
    }

    fun deleteAllNote(){
        DeleteAllTask().execute()
    }

    private class InsertTask : AsyncTask<Note, Unit, Unit>(){
        override fun doInBackground(vararg notes: Note?) : Unit? {
            if(notes != null && notes.size > 0){
                database.notesDao().insertNote(notes[0]!!)
            }
            return null
        }
    }

    private class DeleteTask : AsyncTask<Note, Unit, Unit>(){
        override fun doInBackground(vararg notes: Note?) : Unit? {
            if(notes != null && notes.size > 0){
                database.notesDao().deleteNote(notes[0]!!)
            }
            return null
        }
    }

    private class DeleteAllTask : AsyncTask<Unit, Unit, Unit>(){
        override fun doInBackground(vararg notes: Unit?) : Unit? {
           database.notesDao().deleteAllNotes()
            return null
        }
    }
}