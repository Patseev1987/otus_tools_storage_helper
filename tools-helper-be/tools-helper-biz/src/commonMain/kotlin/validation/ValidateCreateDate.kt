package validation

import ToolsHelperContext
import helper.errorValidation
import helper.fail
import kotlinx.datetime.Clock
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.validateCreateDate(title: String) = worker {
    this.title = title

    on { orderValidating.createTime.epochSeconds > Clock.System.now().epochSeconds }

    handle {
        fail(
            errorValidation(
                field = "createDate",
                violationCode = "wrongCreateDate",
                description = "createDate must not be in the future"
            )
        )
    }
}