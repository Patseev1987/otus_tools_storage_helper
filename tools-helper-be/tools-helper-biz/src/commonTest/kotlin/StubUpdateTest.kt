import kotlinx.coroutines.test.runTest
import models.*
import stubs.ToolsHelperStubs
import kotlin.test.Test
import kotlin.test.assertEquals

class StubUpdateTest {

    private val processor = ToolsHelperOrderProcessor()
    val id = ToolsHelperOrderId("1337")
    val partCount = 123
    val operationId = ToolsHelperOperationId("1")
    val ownerId = ToolsHelperEmployeeId("uuwue")
    val orderStatus = ToolsHelperOrderStatus.CREATED


    @Test
    fun update() = runTest {

        val ctx = ToolsHelperContext(
            command = ToolsHelperCommand.UPDATE,
            state = ToolsHelperState.NONE,
            workMode = ToolsHelperWorkMode.STUB,
            stubCase = ToolsHelperStubs.SUCCESS,
            orderRequest = ToolsHelperOrder(
                id = id,
                partCount = partCount,
                operationId = operationId,
                ownerId = ownerId,
                orderStatus = orderStatus,
            ),
        )
        processor.exec(ctx)
        assertEquals(id, ctx.orderResponse.id)
        assertEquals(partCount, ctx.orderResponse.partCount)
        assertEquals(ownerId, ctx.orderResponse.ownerId)
        assertEquals(operationId, ctx.orderResponse.operationId)
        assertEquals(orderStatus, ctx.orderResponse.orderStatus)
    }

    @Test
    fun badPartCount() = runTest {
        val ctx = ToolsHelperContext(
            command = ToolsHelperCommand.UPDATE,
            state = ToolsHelperState.NONE,
            workMode = ToolsHelperWorkMode.STUB,
            stubCase = ToolsHelperStubs.BAD_PARTS_COUNT,
            orderRequest = ToolsHelperOrder(
                id = id,
                partCount = -1,
                operationId = operationId,
                ownerId = ownerId,
                orderStatus = orderStatus,
            ),
        )
        processor.exec(ctx)
        assertEquals(ToolsHelperOrder(), ctx.orderResponse)
        assertEquals("part count", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun badId() = runTest {
        val ctx = ToolsHelperContext(
            command = ToolsHelperCommand.UPDATE,
            state = ToolsHelperState.NONE,
            workMode = ToolsHelperWorkMode.STUB,
            stubCase = ToolsHelperStubs.BAD_ID,
            orderRequest = ToolsHelperOrder(
                id = id,
                partCount = -1,
                operationId = operationId,
                ownerId = ownerId,
                orderStatus = orderStatus,
            ),
        )
        processor.exec(ctx)
        assertEquals(ToolsHelperOrder(), ctx.orderResponse)
        assertEquals("id", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun databaseError() = runTest {
        val ctx = ToolsHelperContext(
            command = ToolsHelperCommand.UPDATE,
            state = ToolsHelperState.NONE,
            workMode = ToolsHelperWorkMode.STUB,
            stubCase = ToolsHelperStubs.DB_ERROR,
            orderRequest = ToolsHelperOrder(
                id = id,
                partCount = -1,
                operationId = operationId,
                ownerId = ownerId,
                orderStatus = orderStatus,
            ),
        )
        processor.exec(ctx)
        assertEquals(ToolsHelperOrder(), ctx.orderResponse)
        assertEquals("internal", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun badNoCase() = runTest {
        val ctx = ToolsHelperContext(
            command = ToolsHelperCommand.UPDATE,
            state = ToolsHelperState.NONE,
            workMode = ToolsHelperWorkMode.STUB,
            stubCase = ToolsHelperStubs.CANNOT_DELETE,
            orderRequest = ToolsHelperOrder(
                id = id,
                partCount = -1,
                operationId = operationId,
                ownerId = ownerId,
                orderStatus = orderStatus,
            ),
        )
        processor.exec(ctx)
        assertEquals(ToolsHelperOrder(), ctx.orderResponse)
        assertEquals("stub", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }
}