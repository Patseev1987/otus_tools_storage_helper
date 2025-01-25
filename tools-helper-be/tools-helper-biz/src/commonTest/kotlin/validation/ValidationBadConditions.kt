package validation

import ToolsHelperContext
import ToolsHelperOrderProcessor
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Instant
import models.*
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

fun validationPartCountCorrect(command: ToolsHelperCommand, processor: ToolsHelperOrderProcessor) = runTest {
    val ctx = ToolsHelperContext(
        command = command,
        state = ToolsHelperState.NONE,
        workMode = ToolsHelperWorkMode.TEST,
        orderRequest = ToolsHelperOrder(
            id = ToolsHelperOrderId("111-222-qqq-QQQ"),
            partCount = 10,
            operationId = ToolsHelperOperationId("000-999-ppp-OOO"),
            ownerId = ToolsHelperEmployeeId("777-666-jjj-LLL"),
            orderStatus = ToolsHelperOrderStatus.IN_PROGRESS,
            lock = ToolsHelperOrderLock("111-222-qqq-ZZZ"),
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(ToolsHelperState.FAILING, ctx.state)
}

fun validationPartCountIncorrect(command: ToolsHelperCommand, processor: ToolsHelperOrderProcessor) = runTest {
    val ctx = ToolsHelperContext(
        command = command,
        state = ToolsHelperState.NONE,
        workMode = ToolsHelperWorkMode.TEST,
        orderRequest = ToolsHelperOrder(
            id = ToolsHelperOrderId("111-222-qqq-QQQ"),
            partCount = -10,
            operationId = ToolsHelperOperationId("000-999-ppp-OOO"),
            ownerId = ToolsHelperEmployeeId("777-666-jjj-LLL"),
            orderStatus = ToolsHelperOrderStatus.IN_PROGRESS,
            lock = ToolsHelperOrderLock("111-222-qqq-ZZZ"),
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ToolsHelperState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("partCount", error?.field)
}

fun validationOrderStatusCorrect(command: ToolsHelperCommand, processor: ToolsHelperOrderProcessor) = runTest {
    val ctx = ToolsHelperContext(
        command = command,
        state = ToolsHelperState.NONE,
        workMode = ToolsHelperWorkMode.TEST,
        orderRequest = ToolsHelperOrder(
            id = ToolsHelperOrderId("111-222-qqq-QQQ"),
            partCount = 10,
            operationId = ToolsHelperOperationId("000-999-ppp-OOO"),
            ownerId = ToolsHelperEmployeeId("777-666-jjj-LLL"),
            orderStatus = ToolsHelperOrderStatus.IN_PROGRESS,
            lock = ToolsHelperOrderLock("111-222-qqq-ZZZ"),
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(ToolsHelperState.FAILING, ctx.state)
}

fun validationOrderStatusIncorrect(command: ToolsHelperCommand, processor: ToolsHelperOrderProcessor) = runTest {
    val ctx = ToolsHelperContext(
        command = command,
        state = ToolsHelperState.NONE,
        workMode = ToolsHelperWorkMode.TEST,
        orderRequest = ToolsHelperOrder(
            id = ToolsHelperOrderId("111-222-qqq-QQQ"),
            partCount = 10,
            operationId = ToolsHelperOperationId("000-999-ppp-OOO"),
            ownerId = ToolsHelperEmployeeId("777-666-jjj-LLL"),
            orderStatus = ToolsHelperOrderStatus.NONE,
            lock = ToolsHelperOrderLock("111-222-qqq-ZZZ"),
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ToolsHelperState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("orderStatus", error?.field)
}

fun validationCreateDateCorrect(command: ToolsHelperCommand, processor: ToolsHelperOrderProcessor) = runTest {
    val ctx = ToolsHelperContext(
        command = command,
        state = ToolsHelperState.NONE,
        workMode = ToolsHelperWorkMode.TEST,
        orderRequest = ToolsHelperOrder(
            id = ToolsHelperOrderId("111-222-qqq-QQQ"),
            partCount = 10,
            createTime = Instant.parse("2020-08-30T18:43:00Z"),
            operationId = ToolsHelperOperationId("000-999-ppp-OOO"),
            ownerId = ToolsHelperEmployeeId("777-666-jjj-LLL"),
            orderStatus = ToolsHelperOrderStatus.IN_PROGRESS,
            lock = ToolsHelperOrderLock("111-222-qqq-ZZZ"),
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(ToolsHelperState.FAILING, ctx.state)
}

fun validationCreateDateIncorrect(command: ToolsHelperCommand, processor: ToolsHelperOrderProcessor) = runTest {
    val ctx = ToolsHelperContext(
        command = command,
        state = ToolsHelperState.NONE,
        workMode = ToolsHelperWorkMode.TEST,
        orderRequest = ToolsHelperOrder(
            id = ToolsHelperOrderId("111-222-qqq-QQQ"),
            partCount = 10,
            operationId = ToolsHelperOperationId("000-999-ppp-OOO"),
            createTime = Instant.parse("2025-08-30T18:43:00Z"),
            ownerId = ToolsHelperEmployeeId("777-666-jjj-LLL"),
            orderStatus = ToolsHelperOrderStatus.IN_PROGRESS,
            lock = ToolsHelperOrderLock("111-222-qqq-ZZZ"),
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ToolsHelperState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("createDate", error?.field)
}