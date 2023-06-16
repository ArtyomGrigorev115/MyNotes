package com.kurs2.mynotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter constructor( notes: MutableList<Note>) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val notes: MutableList<Note>
    private var _onNoteClickListener: OnNoteClickListener? = null
    var onNoteClickListener: OnNoteClickListener? = null
        set(value){
            _onNoteClickListener = value
            field = value
        }




    /*Интерфейс слушает шелчки на элементе
    * position - номер позиции элемента на который нажали*/
    interface OnNoteClickListener{
        fun onNoteClick(position: Int): Unit
        fun onLongClick(position: Int): Unit
    }

    init{
        this.notes = notes
    }

     inner class NotesViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        /*так как мы находимся вне активности, то напрямую метод findviewByID() вызвать невозможно,
       * вот для этого в конструкторе и передаётся View*/
        val textViewTitle : TextView by lazy { itemView.findViewById(R.id.textViewTitle) }
         val textViewDescription : TextView by lazy { itemView.findViewById(R.id.textViewDescription) }
         val textViewDayOfWeek : TextView by lazy { itemView.findViewById(R.id.textViewDayOfWeek) }
        // val textViewPriority : TextView by lazy { itemView.findViewById(R.id.textViewPriority) }
         init {
             itemView.setOnClickListener(View.OnClickListener {
                 _onNoteClickListener?.onNoteClick(adapterPosition)
             })
            /* добавить View слушатель долгого нажатия */
            itemView.setOnLongClickListener {
                onNoteClickListener?.onLongClick(adapterPosition)
                true
            }

         }


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
        holder.textViewDayOfWeek.text = Note.getDayAsString(note.dayOfWeek)
        val colorId: Int
        val priority: Int = note.priority
        colorId = when(priority){
            1 -> ResourcesCompat.getColor(holder.itemView.context.resources, android.R.color.holo_red_light, holder.itemView.context.theme)
            2 -> ResourcesCompat.getColor(holder.itemView.context.resources, android.R.color.holo_orange_light, holder.itemView.context.theme)
            else -> ResourcesCompat.getColor(holder.itemView.context.resources, android.R.color.holo_green_light, holder.itemView.context.theme)
        }
        holder.textViewTitle.setBackgroundColor(colorId)

       // holder.textViewPriority.text = note.priority.toString()

    }

    /*Кол-во элементов в списке заметок */
    override fun getItemCount(): Int = notes.size
}