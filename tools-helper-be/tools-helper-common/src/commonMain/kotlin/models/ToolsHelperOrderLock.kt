package models

import kotlin.jvm.JvmInline

@JvmInline
value class ToolsHelperOrderLock(private val id: String) {
    fun asString() = id

    companion object {
        val NONE = ToolsHelperOrderLock("")
    }
}
