package validation

import ToolsHelperContext
import helper.errorValidation
import helper.fail
import models.ToolsHelperOperationId
import models.ToolsHelperOrder
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.validateOperationIdProperFormat(title: String) = worker {
    this.title = title

    on {
        orderValidating.operationId != ToolsHelperOperationId.NONE && !orderValidating.operationId.asString()
            .matches(ToolsHelperOrder.FORMAT_REGEX)
    }
    handle {
        val encodedId = orderValidating.id.asString()
            .replace("<", "&lt;")
            .replace(">", "&gt;")
        fail(
            errorValidation(
                field = "operationId",
                violationCode = "badFormat",
                description = "value $encodedId must contain only letters and numbers"
            )
        )
    }
}