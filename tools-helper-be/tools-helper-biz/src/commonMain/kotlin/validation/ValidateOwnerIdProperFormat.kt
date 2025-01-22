package validation

import ToolsHelperContext
import helper.errorValidation
import helper.fail
import models.ToolsHelperEmployeeId
import models.ToolsHelperOrder
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.validateOwnerIdProperFormat(title: String) = worker {
    this.title = title

    on {
        orderValidating.ownerId != ToolsHelperEmployeeId.NONE && !orderValidating.ownerId.asString()
            .matches(ToolsHelperOrder.FORMAT_REGEX)
    }
    handle {
        val encodedId = orderValidating.id.asString()
            .replace("<", "&lt;")
            .replace(">", "&gt;")
        fail(
            errorValidation(
                field = "ownerId",
                violationCode = "badFormat",
                description = "value $encodedId must contain only letters and numbers"
            )
        )
    }
}