package com.kurs2.mynotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter constructor( notes: MutableList<Note>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val notes: MutableList<Note>

    init{
        this.notes = notes
    }

     inner class NotesViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /*так как мы находимся вне активности, то напрямую метод findviewByID() вызвать невозможно,
       * вот для этого в конструкторе и передаётся View*/
        val textViewTitle : TextView by lazy { itemView.findViewById(R.id.textViewTitle) }
         val textViewDescription : TextView by lazy { itemView.findViewById(R.id.textViewDescription) }
         val textViewDayOfWeek : TextView by lazy { itemView.findViewById(R.id.textViewDayOfWeek) }
         val textViewPriority : TextView by lazy { itemView.findViewById(R.id.textViewPriority) }


    }

    /*Берём макет заметки и передаём его в качестве аргумента в конструктор класса NotesViewHolder*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent,false)
        return NotesViewHolder(view)
    }

    /*Далее в шаблоне, который создали в методе onCreateViewHolder() запоняем все аля данными */
    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val note: Note = notes.get(position)
        holder.textViewTitle.text = note.title
        holder.textViewDescription.text = note.description
        holder.textViewDayOfWeek.text = note.dayOfWeek
        holder.textViewPriority.text = note.priority.toString()

    }

    /*Кол-во элементов в списке заметок */
    override fun getItemCount(): Int = notes.size
}