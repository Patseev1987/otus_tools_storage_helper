package validation

import ToolsHelperContext
import helper.errorValidation
import helper.fail
import models.ToolsHelperState
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.chain
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.validateIds(title: String) = chain {
    this.title = title
    description = """
        Проверка полей с Id:
        - operationId
        - ownerId
    """.trimIndent()

    on { state == ToolsHelperState.RUNNING }

    validateOperationIdNotEmpty("Проверка, ид операции не пустое")
    validateOperationIdProperFormat("Проверка корректного заполнения ид операции")
    validateOwnerIdNotEmpty("Проверка, ид работника не пустое")
    validateOwnerIdProperFormat("Проверка корректного заполнения ид работника")

}