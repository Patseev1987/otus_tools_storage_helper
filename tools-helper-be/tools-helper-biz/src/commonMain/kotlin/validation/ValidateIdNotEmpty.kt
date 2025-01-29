package validation

import ToolsHelperContext
import helper.errorValidation
import helper.fail
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.validateIdNotEmpty(title: String) = worker {
    this.title = title
    on { orderValidating.id.asString().isEmpty() }
    handle {
        fail(
            errorValidation(
                field = "id",
                violationCode = "empty",
                description = "field must not be empty"
            )
        )
    }
}