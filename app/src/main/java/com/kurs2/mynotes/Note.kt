package com.kurs2.mynotes

import java.util.Date

data class Note constructor( val title: String, val description: String, val dayOfWeek: Int, val priority: Int, val id: Int ){
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
