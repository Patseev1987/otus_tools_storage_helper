package validation

import ToolsHelperContext
import helper.errorValidation
import helper.fail
import models.ToolsHelperOrder
import models.ToolsHelperOrderId
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.validateIdProperFormat(title: String) = worker {
    this.title = title

    on {
        orderValidating.id != ToolsHelperOrderId.NONE && !orderValidating.id.asString()
            .matches(ToolsHelperOrder.FORMAT_REGEX)
    }
    handle {
        val encodedId = orderValidating.id.asString()
            .replace("<", "&lt;")
            .replace(">", "&gt;")
        fail(
            errorValidation(
                field = "id",
                violationCode = "badFormat",
                description = "value $encodedId must contain only letters and numbers"
            )
        )
    }
}