package repo

import models.ToolsHelperError
import models.ToolsHelperOrder

sealed interface IDbOrdersResponse: IDbResponse<List<ToolsHelperOrder>>

data class DbOrdersResponseOk(
    val data: List<ToolsHelperOrder>
): IDbOrdersResponse

data class DbOrdersResponseError(
    val errors: List<ToolsHelperError> = emptyList()
): IDbOrdersResponse {
    constructor(error: ToolsHelperError): this(listOf(error))
}