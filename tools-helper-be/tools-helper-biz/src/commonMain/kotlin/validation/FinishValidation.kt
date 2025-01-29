package validation

import ToolsHelperContext
import models.ToolsHelperState
import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.worker

fun ICorChainDsl<ToolsHelperContext>.finishAdValidation(title: String) = worker {
    this.title = title
    on { state == ToolsHelperState.RUNNING }
    handle {
        orderValidated = orderValidating
    }
}

fun ICorChainDsl<ToolsHelperContext>.finishAdFilterValidation(title: String) = worker {
    this.title = title
    on { state == ToolsHelperState.RUNNING }
    handle {
        orderFilterValidated = orderFilterValidating
    }
}