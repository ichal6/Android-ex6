package pl.lechowicz.android_vi.db

import pl.lechowicz.android_vi.model.Student

interface StudentDao {
    fun getAllStudents(): List<Student>
    fun getStudentById(id: Int): Student?
    fun addStudent(student: Student): Long
    fun updateStudent(student: Student): Int
    fun deleteStudent(student: Student): Int
}
