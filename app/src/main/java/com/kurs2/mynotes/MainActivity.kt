package com.kurs2.mynotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    /*RecyclerView - отображает список заметок*/
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerViewNotes) }

    /*Спосок заметок*/
    private val notes: MutableList<Note> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*заметки в спсике заметок*/
        notes.add( Note("Парикмахер", "Сделать прическу", "Понедельник", 2));
        notes.add( Note("Баскетбол", "Игра со школьной командой", "Вторник", 3));
        notes.add( Note("Магазин", "Купить новые джинсы", "Понедельник", 3));
        notes.add( Note("Стоматолог", "Вылечить зубы", "Понедельник", 2));
        notes.add( Note("Парикмахер", "Сделать прическу к выпускному", "Среда", 1));
        notes.add( Note("Баскетбол", "Игра со школьной командой", "Вторник", 3));
        notes.add( Note("Магазин", "Купить новые джинсы", "Понедельник", 3));

        /*Добавляем Адаптер*/
        val notesAdapter: NotesAdapter = NotesAdapter(notes)
        /*Укажем как распологаем элементы в RecyclerView(вертикаль, горизонталь, сетка)*/
        recyclerView.layoutManager = LinearLayoutManager(this)
        /*Устанавливаем RecyclerView адаптер*/
        recyclerView.adapter = notesAdapter


    }
}