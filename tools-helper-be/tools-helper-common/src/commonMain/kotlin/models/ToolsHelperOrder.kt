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
    var tools: Map<String, Int> = mutableMapOf(),
    var missingTools: Map<String, Int> = mutableMapOf(),
    var lock: ToolsHelperOrderLock = ToolsHelperOrderLock.NONE,
    val permissionsClient: MutableSet<ToolsHelperOrderPermissionClient> = mutableSetOf()
) {
    fun isEmpty() = this == NONE

    fun deepCopy() = copy(
        permissionsClient = permissionsClient.toMutableSet(),
        tools = tools.toMutableMap(),
        missingTools = missingTools.toMutableMap()
    )

    companion object {
        private val NONE = ToolsHelperOrder()
        val FORMAT_REGEX = Regex("^[0-9a-zA-Z#:-]+$")
    }

}
