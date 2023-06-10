package com.kurs2.mynotes

import java.util.Date

data class Note constructor( val title: String, val description: String, val dayOfWeek: String, val priority: Int, val date: Date? = null ){

}
