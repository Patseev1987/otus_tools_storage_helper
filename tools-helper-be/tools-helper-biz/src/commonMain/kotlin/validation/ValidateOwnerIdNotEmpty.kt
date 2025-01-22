package validation

import ToolsHelperContext
import helper.errorValidation
import helper.fail
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.validateOwnerIdNotEmpty(title: String) = worker {
    this.title = title
    on { orderValidating.ownerId.asString().isEmpty() }
    handle {
        fail(
            errorValidation(
                field = "ownerId",
                violationCode = "empty",
                description = "field must not be empty"
            )
        )
    }
}