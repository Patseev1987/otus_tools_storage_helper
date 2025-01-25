package validation

import ToolsHelperContext
import helper.errorValidation
import helper.fail
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.validatePartCount(title: String) = worker {
    this.title = title

    on { orderValidating.partCount <= 0}

    handle {
        fail(
            errorValidation(
                field = "partCount",
                violationCode = "lower or equal to 0",
                description = "field must not be lower or equal to 0"
            )
        )
    }
}