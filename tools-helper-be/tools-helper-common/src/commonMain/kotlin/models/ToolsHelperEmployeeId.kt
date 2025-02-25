package models

import kotlin.jvm.JvmInline

@JvmInline
value class ToolsHelperEmployeeId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = ToolsHelperEmployeeId("")
    }
}
