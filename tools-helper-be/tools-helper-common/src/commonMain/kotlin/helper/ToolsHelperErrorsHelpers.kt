package helper

import ToolsHelperContext
import models.ToolsHelperError
import models.ToolsHelperState
import ru.patseev.helper.logging.LogLevel

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

inline fun errorValidation(
    field: String,
    violationCode: String,
    description: String,
    level: LogLevel = LogLevel.ERROR,
) = ToolsHelperError(
    code = "validation-$field-$violationCode",
    field = field,
    group = "validation",
    message = "Validation error for field $field: $description",
    level = level,
)