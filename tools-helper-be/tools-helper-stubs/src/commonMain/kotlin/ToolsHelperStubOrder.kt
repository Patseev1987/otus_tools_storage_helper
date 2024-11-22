package ru.otus.otuskotlin.marketplace.stubs

import models.*

object ToolsHelperStubOrder {
    val Order_CREATED_NEW_ORDER: ToolsHelperOrder
        get() = ToolsHelperOrder(
            id = ToolsHelperOrderId("777"),
            operationId = ToolsHelperOperationId("111"),
            ownerId = ToolsHelperEmployeeId("7"),
            orderStatus = ToolsHelperOrderStatus.CREATED,
            partCount = 99,
            tools = mutableMapOf(
                "2004-9060" to 10,
                "3070-8811" to 1
            ),
            permissionsClient = mutableSetOf(
                ToolsHelperOrderPermissionClient.READ,
                ToolsHelperOrderPermissionClient.DELETE,
                ToolsHelperOrderPermissionClient.UPDATE,
                ToolsHelperOrderPermissionClient.CHANGE_STATUS,
            )
        )
    val Order_FINISHED_NEW_ORDER = Order_CREATED_NEW_ORDER.copy(orderStatus = ToolsHelperOrderStatus.FINISHED)
}

