package repo

import models.ToolsHelperError

interface IRepoOrder {
    suspend fun createOrder(rq: DbOrderRequest): IDbOrderResponse
    suspend fun readOrder(rq: DbOrderIdRequest): IDbOrderResponse
    suspend fun updateOrder(rq: DbOrderRequest): IDbOrderResponse
    suspend fun deleteOrder(rq: DbOrderIdRequest): IDbOrderResponse
    suspend fun searchOrder(rq: DbOrderFilterRequest): IDbOrdersResponse
    companion object {
        val NONE = object : IRepoOrder {
            override suspend fun createOrder(rq: DbOrderRequest): IDbOrderResponse {
                return DbOrderResponseError(ToolsHelperError("createOrder"))
            }

            override suspend fun readOrder(rq: DbOrderIdRequest): IDbOrderResponse {
                return DbOrderResponseError(ToolsHelperError("readOrder"))
            }

            override suspend fun updateOrder(rq: DbOrderRequest): IDbOrderResponse {
                return DbOrderResponseError(ToolsHelperError("updateOrder"))
            }

            override suspend fun deleteOrder(rq: DbOrderIdRequest): IDbOrderResponse {
                return DbOrderResponseError(ToolsHelperError("deleteOrder"))
            }

            override suspend fun searchOrder(rq: DbOrderFilterRequest): IDbOrdersResponse {
                return DbOrdersResponseError(ToolsHelperError("searchOrder"))
            }
        }
    }
}