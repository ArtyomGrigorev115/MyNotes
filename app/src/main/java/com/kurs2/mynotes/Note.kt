package com.kurs2.mynotes

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID

@Entity(tableName = "notes")
 class Note
{
    var title: String
        set(value) {field = value}
        get() = field
    var description: String
        set(value) {field = value}
        get() = field
    var dayOfWeek: Int
        set(value) {field = value}
        get() = field
    var priority: Int
        set(value) {field = value}
        get() = field
    @PrimaryKey(autoGenerate = true)  var id: Int = 0
        set(value) {field = value}
        get() = field
   public constructor(title: String, description: String, dayOfWeek: Int, priority: Int, id: Int){
        this.title = title
        this.description = description
        this.dayOfWeek = dayOfWeek
        this.priority = priority
        this.id = id
    }
    @Ignore
    constructor(title: String, description: String, dayOfWeek: Int, priority: Int) {
        this.title = title
        this.description = description
        this.dayOfWeek = dayOfWeek
        this.priority = priority

    }

    companion object{
        fun getDayAsString(position: Int): String{
             val r: String = when(position){
                1->  "Понедельник"
                2->  "Вторник"
                3->  "Среда"
                4->  "Четверг"
                5->  "Пятница"
                6->  "Суббота"
                7->  "Воскресенье"
                 else -> {"Ошибка"}
             }
            return r
        }
    }
}
