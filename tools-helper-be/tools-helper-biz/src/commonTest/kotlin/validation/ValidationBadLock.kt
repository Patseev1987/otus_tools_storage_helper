package validation

import ToolsHelperContext
import ToolsHelperOrderProcessor
import kotlinx.coroutines.test.runTest
import models.*
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

fun validationLockCorrect(command: ToolsHelperCommand, processor: ToolsHelperOrderProcessor) = runTest {
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

fun validatingLockTrim(command: ToolsHelperCommand, processor: ToolsHelperOrderProcessor) = runTest {
    val ctx = ToolsHelperContext(
        command = command,
        state = ToolsHelperState.NONE,
        workMode = ToolsHelperWorkMode.TEST,
        orderRequest = ToolsHelperOrder(
            id = ToolsHelperOrderId("   \n111-222-qqq-QQQ   \t"),
            partCount = 10,
            operationId = ToolsHelperOperationId("000-999-ppp-OOO"),
            ownerId = ToolsHelperEmployeeId("777-666-jjj-LLL"),
            orderStatus = ToolsHelperOrderStatus.IN_PROGRESS,
            lock = ToolsHelperOrderLock("\t\t\n\t 111-222-qqq-ZZZ"),
        ),
    )
    processor.exec(ctx)
    assertEquals(0, ctx.errors.size)
    assertNotEquals(ToolsHelperState.FAILING, ctx.state)
}

fun validatingLockEmpty(command: ToolsHelperCommand, processor: ToolsHelperOrderProcessor) = runTest {
    val ctx = ToolsHelperContext(
        command = command,
        state = ToolsHelperState.NONE,
        workMode = ToolsHelperWorkMode.TEST,
        orderRequest = ToolsHelperOrder(
            id = ToolsHelperOrderId("111-222-qqq-ZZZ"),
            partCount = 10,
            operationId = ToolsHelperOperationId("777-666-jjj-LLL"),
            ownerId = ToolsHelperEmployeeId("777-666-jjj-LLL"),
            orderStatus = ToolsHelperOrderStatus.IN_PROGRESS,
            lock = ToolsHelperOrderLock.NONE,
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ToolsHelperState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("lock", error?.field)
    assertContains(error?.message ?: "", "id")
}

fun validatingLockFormat(command: ToolsHelperCommand, processor: ToolsHelperOrderProcessor) = runTest {
    val ctx = ToolsHelperContext(
        command = command,
        state = ToolsHelperState.NONE,
        workMode = ToolsHelperWorkMode.TEST,
        orderRequest = ToolsHelperOrder(
            id = ToolsHelperOrderId("000-999-ppp-OOO"),
            partCount = 10,
            operationId = ToolsHelperOperationId("000-999-ppp-OOO"),
            ownerId = ToolsHelperEmployeeId("777-666-jjj-LLL"),
            orderStatus = ToolsHelperOrderStatus.IN_PROGRESS,
            lock = ToolsHelperOrderLock("*&!@^$*&!#"),
        ),
    )
    processor.exec(ctx)
    assertEquals(1, ctx.errors.size)
    assertEquals(ToolsHelperState.FAILING, ctx.state)
    val error = ctx.errors.firstOrNull()
    assertEquals("lock", error?.field)
    assertContains(error?.message ?: "", "id")
}