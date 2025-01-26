package repo

import models.ToolsHelperError
import models.ToolsHelperOrder

sealed interface IDbOrderResponse: IDbResponse<ToolsHelperOrder>

data class DbOrderResponseOk(
    val data: ToolsHelperOrder
): IDbOrderResponse

data class DbOrderResponseError(
    val errors: List<ToolsHelperError> = emptyList()
): IDbOrderResponse{
    constructor(error: ToolsHelperError): this(listOf(error))
}

data class DbOrderResponseErrorWithData(
    val data: ToolsHelperOrder,
    val errors: List<ToolsHelperError> = emptyList()
): IDbOrderResponse{
    constructor(order:ToolsHelperOrder, error: ToolsHelperError): this(order, listOf(error))
}

