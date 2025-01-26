package repo

import models.ToolsHelperOrder
import models.ToolsHelperOrderId

data class DbOrderIdRequest(
    val id: ToolsHelperOrderId
) {
    constructor(order: ToolsHelperOrder) : this(order.id)
}