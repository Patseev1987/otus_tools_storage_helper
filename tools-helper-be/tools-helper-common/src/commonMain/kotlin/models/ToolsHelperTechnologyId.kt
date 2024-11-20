package models

import kotlin.jvm.JvmInline

@JvmInline
value class ToolsHelperTechnologyId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = ToolsHelperTechnologyId("")
    }
}
