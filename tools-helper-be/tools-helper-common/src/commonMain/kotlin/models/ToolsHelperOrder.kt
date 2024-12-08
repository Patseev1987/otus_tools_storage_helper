package models

import NONE
import kotlinx.datetime.Instant


data class ToolsHelperOrder(
    var id: ToolsHelperOrderId = ToolsHelperOrderId.NONE,
    var operationId: ToolsHelperOperationId = ToolsHelperOperationId.NONE,
    var ownerId: ToolsHelperEmployeeId = ToolsHelperEmployeeId.NONE,
    var orderStatus: ToolsHelperOrderStatus = ToolsHelperOrderStatus.NONE,
    var partCount: Int = 0,
    var createTime: Instant = Instant.NONE,
    val tools: Map<String, Int> = mutableMapOf(),
    val missingTools: Map<String, Int> = mutableMapOf(),
    var lock: ToolsHelperOrderLock = ToolsHelperOrderLock.NONE,
    val permissionsClient: MutableSet<ToolsHelperOrderPermissionClient> = mutableSetOf()
) {
    fun isEmpty() = this == NONE

    companion object {
        private val NONE = ToolsHelperOrder()
    }

}
