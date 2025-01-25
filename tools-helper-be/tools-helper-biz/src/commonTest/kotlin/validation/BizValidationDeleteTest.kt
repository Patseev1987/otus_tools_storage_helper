package validation

import models.ToolsHelperCommand
import kotlin.test.Test

class BizValidationDeleteTest() : BaseValidationTest(){
    override val command = ToolsHelperCommand.DELETE

    @Test fun correctId() = validationIdCorrect(command, processor)
    @Test fun trimId() = validatingIdTrim(command, processor)
    @Test fun emptyId() = validatingIdEmpty(command, processor)
    @Test fun badFormatId() = validatingIdFormat(command, processor)

    @Test fun correctLockId() = validationLockCorrect(command, processor)
    @Test fun trimLockId() = validatingLockTrim(command, processor)
    @Test fun emptyLockId() = validatingLockEmpty(command, processor)
    @Test fun badFormatLockId() = validatingLockFormat(command, processor)

}