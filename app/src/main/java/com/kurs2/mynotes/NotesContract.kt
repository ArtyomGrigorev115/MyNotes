package com.kurs2.mynotes

import android.provider.BaseColumns
import android.provider.BaseColumns._ID

/*Класс хранит всю информацию о базе данных:
* назване таблицы, заголовки столбцов итд
*
* Вложенные классы - это таблицы в базе
* Вложенные классы, которые обозначают таблицы имеют суффикс Entry*/
class NotesContract {
    companion object{
        final class NotesEntry : BaseColumns{

            companion object{
                val TABLE_NAME: String = "notes"
                val COLUMN_TITLE: String = "title"
                val COLUMN_DESCRIPTION: String = "description"
                val COLUMN_DAY_OF_WEEK: String = "day_of_week"
                val COLUMN_PRIORITY: String = "priority"

                val TYPE_TEXT = "TEXT"
                val TYPE_INTEGER = "INTEGER"
                public var id = _ID

                public val CREATE_COMMAND: String = "CREATE TABLE IF NOT EXISTS $TABLE_NAME ($_ID $TYPE_INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TITLE $TYPE_TEXT, $COLUMN_DESCRIPTION $TYPE_TEXT, $COLUMN_DAY_OF_WEEK $TYPE_INTEGER, $COLUMN_PRIORITY $TYPE_INTEGER)"
                val DROP_COMMAND: String = "DROP TABLE IF EXISTS $TABLE_NAME"

            }



        }
    }

}