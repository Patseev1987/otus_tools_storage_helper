package validation

import models.ToolsHelperCommand
import kotlin.test.Test

class BizValidationReadTest: BaseValidationTest() {
    override val command = ToolsHelperCommand.READ

    @Test fun correctId() = validationIdCorrect(command, processor)
    @Test fun trimId() = validatingIdTrim(command, processor)
    @Test fun emptyId() = validatingIdEmpty(command, processor)
    @Test fun badFormatId() = validatingIdFormat(command, processor)
}