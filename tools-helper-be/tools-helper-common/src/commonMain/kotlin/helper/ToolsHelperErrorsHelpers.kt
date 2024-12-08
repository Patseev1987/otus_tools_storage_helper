package helper

import models.ToolsHelperError

fun Throwable.asToolsHelperError(
    code: String = "unknown",
    group: String = "exceptions",
    message: String = this.message ?: "",
) = ToolsHelperError(
    code = code,
    group = group,
    field = "",
    message = message,
    exception = this,
)