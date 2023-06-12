package com.kurs2.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import com.kurs2.mynotes.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_add_note)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)




    }

    fun onClickSaveNote(view: View) {
        val title: String = with(binding.editTextTitle){
            text.toString().trim()
        }
        val description: String = with(binding.editTextDescription){
            text.toString().trim()
        }
        val dayOfWeek: String = with(binding.spinnerDaysOfWeek){
            selectedItem.toString()
        }
        val radioButtonId: Int = with(binding.radioGroupPriority){
            checkedRadioButtonId
        }

        val radioButton: RadioButton = findViewById(radioButtonId)
        val priority: Int = radioButton.text.toString().toInt()

        val note: Note = Note(title, description, dayOfWeek, priority)
        MainActivity.notes.add(note)
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}