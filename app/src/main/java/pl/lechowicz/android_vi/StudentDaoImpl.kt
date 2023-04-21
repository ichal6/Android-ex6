package pl.lechowicz.android_vi

import android.content.ContentValues

class StudentDaoImpl(private val dbHelper: DbHelper) : StudentDao {

    override fun getAllStudents(): List<Student> {
        val students = mutableListOf<Student>()
        val db = dbHelper.readableDatabase
        val query = "SELECT * FROM ${StudentTable.TABLE_NAME}"
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            try {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(StudentTable.Columns.ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(StudentTable.Columns.NAME))
                val surname = cursor.getString(cursor.getColumnIndexOrThrow(StudentTable.Columns.SURNAME))
                students.add(Student(id, name, surname))
            } catch (ex: IllegalArgumentException) {
                System.err.println("problem with DB: $ex");
            }
        }
        cursor.close()
        db.close()
        return students
    }

    override fun getStudentById(id: Int): Student? {
        val db = dbHelper.readableDatabase
        val query = "SELECT * FROM ${StudentTable.TABLE_NAME} WHERE ${StudentTable.Columns.ID} = ?"
        val cursor = db.rawQuery(query, arrayOf(id.toString()))
        val student: Student? = if (cursor.moveToFirst()) {
            val columnIndexName = cursor.getColumnIndex(StudentTable.Columns.NAME)
            val columnIndexSurname = cursor.getColumnIndex(StudentTable.Columns.SURNAME)
            if( columnIndexName >= 0 && columnIndexSurname >= 0) {
                val name = cursor.getString(columnIndexName)
                val surname = cursor.getString(columnIndexSurname)
                Student(id, name, surname)
            } else {
                null
            }
        } else {
            null
        }
        cursor.close()
        db.close()
        return student
    }

    override fun addStudent(student: Student): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(StudentTable.Columns.NAME, student.name)
            put(StudentTable.Columns.SURNAME, student.surname)
        }
        val id = db.insert(StudentTable.TABLE_NAME, null, values)
        db.close()
        return id
    }

    override fun updateStudent(student: Student): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(StudentTable.Columns.NAME, student.name)
            put(StudentTable.Columns.SURNAME, student.surname)
        }
        val rowsUpdated = db.update(
            StudentTable.TABLE_NAME,
            values,
            "${StudentTable.Columns.ID} = ?",
            arrayOf(student.id.toString())
        )
        db.close()
        return rowsUpdated
    }

    override fun deleteStudent(student: Student): Int {
        val db = dbHelper.writableDatabase
        val rowsDeleted = db.delete(
            StudentTable.TABLE_NAME,
            "${StudentTable.Columns.ID} = ?",
            arrayOf(student.id.toString())
        )
        db.close()
        return rowsDeleted
    }
}
