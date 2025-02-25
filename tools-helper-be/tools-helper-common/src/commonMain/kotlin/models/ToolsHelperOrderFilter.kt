package models

data class ToolsHelperOrderFilter(
    var searchString: String = "",
    var ownerId: ToolsHelperEmployeeId = ToolsHelperEmployeeId.NONE,
) {
    companion object {
        const val MAX_SEARCH_STRING_LENGTH = 60
    }
}
