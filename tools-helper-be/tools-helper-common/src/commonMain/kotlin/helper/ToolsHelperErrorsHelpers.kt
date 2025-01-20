package helper

import ToolsHelperContext
import models.ToolsHelperError
import models.ToolsHelperState

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

inline fun ToolsHelperContext.addError(vararg error: ToolsHelperError) = errors.addAll(error)

inline fun ToolsHelperContext.fail(error: ToolsHelperError) {
    addError(error)
    state = ToolsHelperState.FAILING
}
