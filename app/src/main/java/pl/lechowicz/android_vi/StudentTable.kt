package pl.lechowicz.android_vi

object StudentTable {
    const val TABLE_NAME = "students"
    object Columns {
        const val ID = "_id"
        const val NAME = "name"
        const val SURNAME = "surname"
    }
    val CREATE_QUERY = """
        CREATE TABLE $TABLE_NAME (
            ${Columns.ID} INTEGER PRIMARY KEY,
            ${Columns.NAME} TEXT NOT NULL,
            ${Columns.SURNAME} TEXT NOT NULL
        );
    """.trimIndent()
}
