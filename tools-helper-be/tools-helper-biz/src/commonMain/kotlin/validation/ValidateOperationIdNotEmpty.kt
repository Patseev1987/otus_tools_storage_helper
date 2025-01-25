package validation

import ToolsHelperContext
import helper.errorValidation
import helper.fail
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.validateOperationIdNotEmpty(title: String) = worker {
    this.title = title
    on { orderValidating.operationId.asString().isEmpty() }
    handle {
        fail(
            errorValidation(
                field = "operationId",
                violationCode = "empty",
                description = "field must not be empty"
            )
        )
    }
}