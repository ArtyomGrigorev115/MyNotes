package com.kurs2.mynotes

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.lifecycle.ViewModelProviders
import com.kurs2.mynotes.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
//    private val dbHelper: NotesDBHelper by lazy { NotesDBHelper(this) }
//    private val database: SQLiteDatabase by lazy { dbHelper.writableDatabase }
//    private val database: NotesDatabase by lazy { NotesDatabase.getInstance(this) }
/*MainViewModel*/
val viewModel: MainViewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_add_note)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()




    }

    fun onClickSaveNote(view: View) {
        val title: String = with(binding.editTextTitle){
            text.toString().trim()
        }
        val description: String = with(binding.editTextDescription){
            text.toString().trim()
        }
        val dayOfWeek: Int = with(binding.spinnerDaysOfWeek){
            selectedItemPosition
        }
        val radioButtonId: Int = with(binding.radioGroupPriority){
            checkedRadioButtonId
        }

        val radioButton: RadioButton = findViewById(radioButtonId)
        val priority: Int = radioButton.text.toString().toInt()

       // val note: Note = Note(title, description, dayOfWeek, priority)
       // MainActivity.notes.add(note)
//        if(isFilled(title, description)){
//            val contentValues: ContentValues = ContentValues()
//            contentValues.put(NotesContract.Companion.NotesEntry.COLUMN_TITLE, title)
//            contentValues.put(NotesContract.Companion.NotesEntry.COLUMN_DESCRIPTION, description)
//            contentValues.put(NotesContract.Companion.NotesEntry.COLUMN_DAY_OF_WEEK, dayOfWeek + 1)
//            contentValues.put(NotesContract.Companion.NotesEntry.COLUMN_PRIORITY, priority)
//         //   database.insert(NotesContract.Companion.NotesEntry.TABLE_NAME, null, contentValues)
//            val intent: Intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//        else{
//            Toast.makeText(this, getString(R.string.warning_fill_fields), Toast.LENGTH_SHORT).show()
//        }
        if(isFilled(title,description)){
            val note: Note = Note(title, description, dayOfWeek, priority)
           // database.notesDao().insertNote(note)
            viewModel.insertNote(note)
            val intent: Intent = Intent(this, MainActivity::class.java)
           startActivity(intent)
        }
        else{
           Toast.makeText(this, getString(R.string.warning_fill_fields), Toast.LENGTH_SHORT).show()
        }
    }

    private fun isFilled(title: String, description: String): Boolean{
        return title.isNotEmpty() && description.isNotEmpty()
    }
}