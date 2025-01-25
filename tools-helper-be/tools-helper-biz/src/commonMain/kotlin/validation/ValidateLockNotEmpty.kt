package validation

import ToolsHelperContext
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker
import helper.errorValidation
import helper.fail


fun ICorChainDsl<ToolsHelperContext>.validateLockNotEmpty(title: String) = worker {
    this.title = title
    on {
        orderValidating.lock.asString().isEmpty()
    }

    handle {
        fail(
            errorValidation(
                field = "lock",
                violationCode = "empty",
                description = "field must not be empty"
            )
        )
    }
}
