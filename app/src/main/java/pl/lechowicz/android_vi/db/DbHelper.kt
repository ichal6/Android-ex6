package pl.lechowicz.android_vi.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "my_database.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(StudentTable.CREATE_QUERY)
        addStudent(db, "John", "Doe")
        addStudent(db, "Michał", "Lechowicz")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // not implemented yet
    }

    private fun addStudent(db: SQLiteDatabase?, name: String, surname: String): Long? {
        val values = ContentValues().apply {
            put(StudentTable.Columns.NAME, name)
            put(StudentTable.Columns.SURNAME, surname)
        }
       return db?.insert(StudentTable.TABLE_NAME, null, values)
    }
}
