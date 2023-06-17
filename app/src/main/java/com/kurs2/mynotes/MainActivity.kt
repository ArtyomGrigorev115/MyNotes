package com.kurs2.mynotes

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {



    /*RecyclerView - отображает список заметок*/
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recyclerViewNotes) }
    /*Адаптер для RecyclerView*/
    private val notesAdapter: NotesAdapter by lazy { NotesAdapter(notes) }
    /*Объект Helper для работы с БД*/
  //  private val dbHelper: NotesDBHelper = NotesDBHelper(this)

    /*Создаём БД*/
  //  val dataBase: SQLiteDatabase by lazy { dbHelper.writableDatabase }

    /*Room DB*/
    private val database: NotesDatabase by lazy { NotesDatabase.getInstance(this) }


    companion object{
        /*Спосок заметок*/
        val notes: MutableList<Note> = mutableListOf()
       // val notesFromDB: MutableList<Note> = mutableListOf()
    }

    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()


        /*Создаём БД*/
      //  val dataBase: SQLiteDatabase = dbHelper.writableDatabase
        /*удалить все данные из БД*/
       // dataBase.delete(NotesContract.Companion.NotesEntry.TABLE_NAME, null, null)


//        if(notes.isEmpty()) {
//            /*заметки в спсике заметок*/
//            notes.add(Note("Парикмахер", "Сделать прическу", "Понедельник", 2));
//            notes.add(Note("Баскетбол", "Игра со школьной командой", "Вторник", 3));
//            notes.add(Note("Магазин", "Купить новые джинсы", "Понедельник", 3));
//            notes.add(Note("Стоматолог", "Вылечить зубы", "Понедельник", 2));
//            notes.add(Note("Парикмахер", "Сделать прическу к выпускному", "Среда", 1));
//            notes.add(Note("Баскетбол", "Игра со школьной командой", "Вторник", 3));
//            notes.add(Note("Магазин", "Купить новые джинсы", "Понедельник", 3));
//        }
//        /*Пишем данные в БД
//        * данные в БД вставляются при помощи объекта классса ContentValues
//        * у куторого нужно вызвать метод ContentValues::put("ключ", "значение")*/
//        notes.forEach {
//            val contentValues:ContentValues = ContentValues()
//            contentValues.put(NotesContract.Companion.NotesEntry.COLUMN_TITLE, it.title)
//            contentValues.put(NotesContract.Companion.NotesEntry.COLUMN_DESCRIPTION, it.description)
//            contentValues.put(NotesContract.Companion.NotesEntry.COLUMN_DAY_OF_WEEK, it.dayOfWeek)
//            contentValues.put(NotesContract.Companion.NotesEntry.COLUMN_PRIORITY, it.priority)
//            /*Вставлям ContentValues в БД*/
//            dataBase.insert(NotesContract.Companion.NotesEntry.TABLE_NAME, null, contentValues)
//        }
        /*Читаем данные из БД и сохраняем в новый список*/

//        /*Cursor хранит все значения из таблицы с именем TABLE_NAME*/
//        val cursor: Cursor = dataBase.query(NotesContract.Companion.NotesEntry.TABLE_NAME, null, null, null, null, null, null)
//        /*по порядку проходим по всем элементам от нуля, пока существуют элементы метод moveToNext возвращает true*/
//        while (cursor.moveToNext()){
//            val id: Int = cursor.getInt(cursor.getColumnIndex(NotesContract.Companion.NotesEntry.id))
//            val title: String = cursor.getString(cursor.getColumnIndex(NotesContract.Companion.NotesEntry.COLUMN_TITLE))
//            val description: String = cursor.getString(cursor.getColumnIndex(NotesContract.Companion.NotesEntry.COLUMN_DESCRIPTION))
//            val dayOfWeek: String = cursor.getString(cursor.getColumnIndex(NotesContract.Companion.NotesEntry.COLUMN_DAY_OF_WEEK))
//            val priority: Int = cursor.getInt(cursor.getColumnIndex(NotesContract.Companion.NotesEntry.COLUMN_PRIORITY))
//
//            val note: Note = Note(title, description, dayOfWeek, priority, id)
//            notes.add(note)
//        }
//        cursor.close()
      //  getData()
        /*Укажем как распологаем элементы в RecyclerView(вертикаль, горизонталь, сетка)*/
        recyclerView.layoutManager = LinearLayoutManager(this)
        getData()
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
//        val id: Int = notes.get(position).id
//        val where: String = "${NotesContract.Companion.NotesEntry.id} = ?"
//        val whereArgs: Array<String> = arrayOf(id.toString())
     //   dataBase.delete(NotesContract.Companion.NotesEntry.TABLE_NAME, where, whereArgs)
       // notes.removeAt(position)
      //  getData()
        val note: Note = notes.get(position)
        database.notesDao().deleteNote(note)
        getData()
        notesAdapter.notifyDataSetChanged()
    }

    fun onclickAddNote(view: View) {
        val intent: Intent  = Intent(this, AddNoteActivity::class.java)
        startActivity(intent)
    }

    private fun getData(){
        val notesFromDb: List<Note> = database.notesDao().getAllNotes()
        notes.clear()
        notes.addAll(notesFromDb)
    }

//    @SuppressLint("Range")
//    private fun getData(): Unit{
//        notes.clear()
//        /*Вывести заметки с конкретным приоритетом*/
//        val selection: String = "${NotesContract.Companion.NotesEntry.COLUMN_PRIORITY} < ?"
//        val selectionArgs: Array<String> = arrayOf("2")
//        /*Вывести заметки с конкретным приоритетом*/
//        val selectionDayOfWeek: String = "${NotesContract.Companion.NotesEntry.COLUMN_DAY_OF_WEEK} == ?"
//        val selectionArgsDayOfWeek: Array<String> = arrayOf("1")
//
//        /*Cursor хранит все значения из таблицы с именем TABLE_NAME*/
//      //  val cursor: Cursor = dataBase.query(NotesContract.Companion.NotesEntry.TABLE_NAME, null, null/*selection*/, null/*selectionArgs*/, null, null, NotesContract.Companion.NotesEntry.COLUMN_DAY_OF_WEEK)
//        /*по порядку проходим по всем элементам от нуля, пока существуют элементы метод moveToNext возвращает true*/
//        while (cursor.moveToNext()){
//            val id: Int = cursor.getInt(cursor.getColumnIndex(NotesContract.Companion.NotesEntry.id))
//            val title: String = cursor.getString(cursor.getColumnIndex(NotesContract.Companion.NotesEntry.COLUMN_TITLE))
//            val description: String = cursor.getString(cursor.getColumnIndex(NotesContract.Companion.NotesEntry.COLUMN_DESCRIPTION))
//            val dayOfWeek: Int = cursor.getInt(cursor.getColumnIndex(NotesContract.Companion.NotesEntry.COLUMN_DAY_OF_WEEK))
//            val priority: Int = cursor.getInt(cursor.getColumnIndex(NotesContract.Companion.NotesEntry.COLUMN_PRIORITY))
//
//            val note: Note = Note(title, description, dayOfWeek, priority, id)
//            notes.add(note)
//        }
//        cursor.close()
//    }
}