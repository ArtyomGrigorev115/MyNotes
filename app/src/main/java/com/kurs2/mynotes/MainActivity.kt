package com.kurs2.mynotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {



    /*RecyclerView - отображает список заметок*/
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerViewNotes) }
    /*Адаптер для RecyclerView*/
    private val notesAdapter: NotesAdapter by lazy { NotesAdapter(notes) }



    companion object{
        /*Спосок заметок*/
        val notes: MutableList<Note> = mutableListOf()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(notes.isEmpty()) {
            /*заметки в спсике заметок*/
            notes.add(Note("Парикмахер", "Сделать прическу", "Понедельник", 2));
            notes.add(Note("Баскетбол", "Игра со школьной командой", "Вторник", 3));
            notes.add(Note("Магазин", "Купить новые джинсы", "Понедельник", 3));
            notes.add(Note("Стоматолог", "Вылечить зубы", "Понедельник", 2));
            notes.add(Note("Парикмахер", "Сделать прическу к выпускному", "Среда", 1));
            notes.add(Note("Баскетбол", "Игра со школьной командой", "Вторник", 3));
            notes.add(Note("Магазин", "Купить новые джинсы", "Понедельник", 3));
        }

        /*Укажем как распологаем элементы в RecyclerView(вертикаль, горизонталь, сетка)*/
        recyclerView.layoutManager = LinearLayoutManager(this)
        /*Устанавливаем RecyclerView адаптер*/
        recyclerView.adapter = notesAdapter
        /*установим слушатель у адаптера
        * который будет удалять элементы из списка
        * при добавлении/удалении элементов нужно сообщять об этом адаптеру, что данные изменились
        * для этого у нашего адаптера вызовим метод notesAdapter.notifyDataSetChanged()*/
        notesAdapter.onNoteClickListener = object: NotesAdapter.OnNoteClickListener{
            override fun onNoteClick(position: Int) {
                Toast.makeText(applicationContext, "Номер позиции $position", Toast.LENGTH_SHORT).show()
               // Log.e("CLICK", "Нажатие на элеент")
             //   remove(position)
            }

            override fun onLongClick(position: Int) {
                remove(position)
            }
        };
        /*Удаление элемента свайпом*/
        val itemTouchHelper: ItemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                remove(viewHolder.adapterPosition)
            }
        })
        /*Прикрепляем хелпер к RecyclerView*/
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }
    fun remove(position: Int): Unit{
        notes.removeAt(position)
        notesAdapter.notifyDataSetChanged()
    }

    fun onclickAddNote(view: View) {
        val intent: Intent  = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
    }
}