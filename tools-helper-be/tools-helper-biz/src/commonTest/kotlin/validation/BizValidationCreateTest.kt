package validation

import models.ToolsHelperCommand
import kotlin.test.Test

class BizValidationCreateTest() : BaseValidationTest(){
    override val command = ToolsHelperCommand.CREATE

    @Test fun correctOwnerId() = validationOwnerIdCorrect(command, processor)
    @Test fun trimOwnerId() = validatingOwnerIdTrim(command, processor)
    @Test fun emptyOwnerId() = validatingOwnerIdEmpty(command, processor)
    @Test fun badFormatOwnerId() = validatingOwnerIdFormat(command, processor)

    @Test fun correctOperationId() = validationOperationIdCorrect(command, processor)
    @Test fun trimOperationId() = validatingOperationIdTrim(command, processor)
    @Test fun emptyOperationId() = validatingOperationIdEmpty(command, processor)
    @Test fun badFormatOperationId() = validatingOperationIdFormat(command, processor)

    @Test fun correctLockId() = validationOperationIdCorrect(command, processor)
    @Test fun trimLockId() = validatingOperationIdTrim(command, processor)
    @Test fun emptyLockId() = validatingOperationIdEmpty(command, processor)
    @Test fun badFormatLockId() = validatingOperationIdFormat(command, processor)

    @Test fun correctPartCount() = validationPartCountCorrect(command, processor)
    @Test fun incorrectPartCount() = validationPartCountIncorrect(command, processor)
    @Test fun correctOrderStatus() = validationOrderStatusCorrect(command, processor)
    @Test fun incorrectOrderStatus() = validationOrderStatusIncorrect(command, processor)
    @Test fun correctCreateDAte() = validationCreateDateCorrect(command, processor)
    @Test fun incorrectCreateDAte() = validationCreateDateIncorrect(command, processor)

}