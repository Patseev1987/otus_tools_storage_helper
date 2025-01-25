import kotlinx.coroutines.test.runTest
import models.*
import stubs.ToolsHelperStubs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class StubSearchTest {
    private val filter = ToolsHelperOrderFilter("22")
    private val processor = ToolsHelperOrderProcessor()

    @Test
    fun search() = runTest {

        val ctx = ToolsHelperContext(
            command = ToolsHelperCommand.SEARCH,
            state = ToolsHelperState.NONE,
            workMode = ToolsHelperWorkMode.STUB,
            stubCase = ToolsHelperStubs.SUCCESS,
            orderFilterRequest = filter,
        )
        processor.exec(ctx)
        assertTrue(ctx.ordersResponses.size > 1)
        val first = ctx.ordersResponses.firstOrNull() ?: fail("Empty response list")
        assertTrue(first.operationId.asString().contains(filter.searchString))
        with (ToolsHelperStub.get()) {
            assertEquals(partCount, first.partCount)
        }
    }

    @Test
    fun badId() = runTest {
        val ctx = ToolsHelperContext(
            command = ToolsHelperCommand.SEARCH,
            state = ToolsHelperState.NONE,
            workMode = ToolsHelperWorkMode.STUB,
            stubCase = ToolsHelperStubs.BAD_ID,
            orderFilterRequest = filter,
        )
        processor.exec(ctx)
        assertEquals(ToolsHelperOrder(), ctx.orderResponse)
        assertEquals("id", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }


    @Test
    fun databaseError() = runTest {
        val ctx = ToolsHelperContext(
            command = ToolsHelperCommand.SEARCH,
            state = ToolsHelperState.NONE,
            workMode = ToolsHelperWorkMode.STUB,
            stubCase = ToolsHelperStubs.DB_ERROR,
            orderFilterRequest = filter,
        )
        processor.exec(ctx)
        assertEquals(ToolsHelperOrder(), ctx.orderResponse)
        assertEquals("internal", ctx.errors.firstOrNull()?.group)
    }

    @Test
    fun badNoCase() = runTest {
        val ctx = ToolsHelperContext(
            command = ToolsHelperCommand.SEARCH,
            state = ToolsHelperState.NONE,
            workMode = ToolsHelperWorkMode.STUB,
            stubCase = ToolsHelperStubs.CANNOT_DELETE,
            orderFilterRequest = filter,
        )
        processor.exec(ctx)
        assertEquals(ToolsHelperOrder(), ctx.orderResponse)
        assertEquals("stub", ctx.errors.firstOrNull()?.field)
        assertEquals("validation", ctx.errors.firstOrNull()?.group)
    }
}