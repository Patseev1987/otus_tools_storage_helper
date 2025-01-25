package validation

import ToolsHelperContext
import helper.errorValidation
import helper.fail
import models.ToolsHelperOrder
import models.ToolsHelperOrderLock
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.validateLockProperFormat(title: String) = worker {
    this.title = title

    on {
        orderValidating.lock != ToolsHelperOrderLock.NONE && !orderValidating.lock.asString()
            .matches(ToolsHelperOrder.FORMAT_REGEX)
    }
    handle {
        val encodedId = orderValidating.lock.asString()
        fail(
            errorValidation(
                field = "lock",
                violationCode = "badFormat",
                description = "value $encodedId must contain only letters and numbers"
            )
        )
    }
}
