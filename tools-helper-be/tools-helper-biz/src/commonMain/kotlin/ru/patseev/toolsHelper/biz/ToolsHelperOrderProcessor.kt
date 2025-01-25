package ru.patseev.toolsHelper.biz

import ToolsHelperContext
import ToolsHelperCorSettings
import ToolsHelperStub
import models.ToolsHelperOrderStatus
import models.ToolsHelperState


@Suppress("unused")
class ToolsHelperOrderProcessor(val corSettings: ToolsHelperCorSettings) {
    suspend fun exec(ctx: ToolsHelperContext) {
        ctx.orderResponse = ToolsHelperStub.get()
        ctx.ordersResponses = ToolsHelperStub
            .prepareSearchList(
                ownerId = "777111",
                operationId = "R2D2",
                status = ToolsHelperOrderStatus.COMPLETED
            )
            .toMutableList()
        ctx.state = ToolsHelperState.RUNNING
    }
}
