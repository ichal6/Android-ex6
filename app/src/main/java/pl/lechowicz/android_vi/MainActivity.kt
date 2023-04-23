package pl.lechowicz.android_vi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import pl.lechowicz.android_vi.db.DbHelper
import pl.lechowicz.android_vi.db.StudentDao
import pl.lechowicz.android_vi.db.StudentDaoImpl

class MainActivity : AppCompatActivity() {

    private lateinit var listViewStudents: ListView
    private lateinit var buttonAddStudent: Button
    private lateinit var studentDao: StudentDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewStudents = findViewById(R.id.listViewStudents)
        buttonAddStudent = findViewById(R.id.buttonAddStudent)

        val dbHelper = DbHelper(this)
        studentDao = StudentDaoImpl(dbHelper)

        buttonAddStudent.setOnClickListener {
            //TODO: add logic for adding a new student
        }

        loadStudents()
    }

    private fun loadStudents() {
        val students = studentDao.getAllStudents()
        val studentNames = students.map { "${it.name} ${it.surname}" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentNames)
        listViewStudents.adapter = adapter
    }

}
