package validation

import ToolsHelperContext
import helper.errorValidation
import helper.fail
import models.ToolsHelperOrderStatus
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.validateOrderStatus(title: String) = worker {
    this.title = title
    on {
        orderValidating.orderStatus == ToolsHelperOrderStatus.NONE
    }
    handle {
        fail(
            errorValidation(
                field = "orderStatus",
                violationCode = "badStatus",
                description = "value ${orderValidating.orderStatus} must be different"
            )
        )
    }
}