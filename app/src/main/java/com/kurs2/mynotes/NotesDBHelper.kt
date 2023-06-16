package com.kurs2.mynotes

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Помошник для работы с БД
 *
 * @property DB_NAME
 * @property DB_VERSION
 * @constructor
 *
 * @param context
 */
class NotesDBHelper constructor(context: Context,
                                private val DB_NAME : String = "notes.db",
    /*factory: SQLiteDatabase.CursorFactory,*/
                                private val DB_VERSION: Int = 2) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {


    /*Вызывается при создании БД*/
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(NotesContract.Companion.NotesEntry.CREATE_COMMAND)
    }

    /*При обновлении базы удаляем старую таблицу и создаём новую*/
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(NotesContract.Companion.NotesEntry.DROP_COMMAND)
        onCreate(db)
    }
}