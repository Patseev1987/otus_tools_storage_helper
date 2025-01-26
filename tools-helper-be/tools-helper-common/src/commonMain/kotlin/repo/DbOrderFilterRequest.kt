package repo

import models.ToolsHelperEmployeeId
import models.ToolsHelperOrderStatus

data class DbOrderFilterRequest(
    val titleFilter: String = "",
    val ownerId: ToolsHelperEmployeeId = ToolsHelperEmployeeId.NONE,
    val orderStatus: ToolsHelperOrderStatus = ToolsHelperOrderStatus.NONE
)
