package pl.lechowicz.android_vi

interface StudentDao {
    fun getAllStudents(): List<Student>
    fun getStudentById(id: Int): Student?
    fun addStudent(student: Student): Long
    fun updateStudent(student: Student): Int
    fun deleteStudent(student: Student): Int
}
