package models

import kotlin.jvm.JvmInline

@JvmInline
value class ToolsHelperOrderId(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = ToolsHelperOrderId("")
    }
}
