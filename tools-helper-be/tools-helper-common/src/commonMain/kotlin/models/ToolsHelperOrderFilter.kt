package models

data class ToolsHelperOrderFilter(
    var searchString: String = "",
    var ownerId: ToolsHelperEmployeeId = ToolsHelperEmployeeId.NONE,
)
