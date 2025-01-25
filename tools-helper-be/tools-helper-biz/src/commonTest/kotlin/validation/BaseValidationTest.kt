package validation

import ToolsHelperCorSettings
import ToolsHelperOrderProcessor
import models.ToolsHelperCommand

abstract class BaseValidationTest {
    protected abstract val command: ToolsHelperCommand
    private val settings by lazy { ToolsHelperCorSettings() }
    protected val processor by lazy { ToolsHelperOrderProcessor(settings) }
}