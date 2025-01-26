import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.ToolsHelperOperationId
import models.ToolsHelperOrder
import models.ToolsHelperOrderId
import models.ToolsHelperOrderStatus

data class OrderEntity(
    val id: String? = null,
    val operationId: String? = null,
    var ownerId: String? = null,
    var orderStatus: String? = null,
    var partCount: Int? = null,
    var createTime: Long? = null,
    var tools: String? = null,
    var missingTools: String? = null,
    var lock: String? = null
) {
    constructor(model: ToolsHelperOrder) : this(
        id = model.id.asString().takeIf { it.isNotBlank() },
        operationId = model.operationId.asString().takeIf { it.isNotBlank() },
        ownerId = model.ownerId.asString().takeIf { it.isNotBlank() },
        orderStatus = model.orderStatus.takeIf { it != ToolsHelperOrderStatus.NONE }?.name,
        partCount = model.partCount,
        createTime = model.createTime.epochSeconds,
        tools = Json.encodeToString(model.tools),
        missingTools = Json.encodeToString(model.missingTools),
        lock = model.lock.asString().takeIf { it.isNotBlank() }
    )

    fun toInternal() = ToolsHelperOrder(
        id = id?.let { ToolsHelperOrderId(it) } ?: ToolsHelperOrderId.NONE,
        operationId = operationId?.let { ToolsHelperOperationId(it) } ?: ToolsHelperOperationId.NONE,

    )
}
