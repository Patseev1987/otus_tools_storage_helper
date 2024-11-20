package models

import kotlin.jvm.JvmInline

@JvmInline
value class ToolsHelperRequestId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = ToolsHelperRequestId("")
    }
}
