package validation

import ToolsHelperContext
import kotlinx.coroutines.test.runTest
import models.ToolsHelperOrderFilter
import models.ToolsHelperState
import ru.patseev.helper.cor.rootChain
import kotlin.test.Test
import kotlin.test.assertEquals

class ValidateSearchStringLengthTest {
    @Test
    fun emptyString() = runTest {
        val ctx = ToolsHelperContext(
            state = ToolsHelperState.RUNNING,
            orderFilterValidating = ToolsHelperOrderFilter(searchString = "")
        )
        chain.exec(ctx)
        assertEquals(ToolsHelperState.RUNNING, ctx.state)
        assertEquals(0, ctx.errors.size)
    }

    @Test
    fun blankString() = runTest {
        val ctx = ToolsHelperContext(
            state = ToolsHelperState.RUNNING,
            orderFilterValidating = ToolsHelperOrderFilter(searchString = "  ")
        )
        chain.exec(ctx)
        assertEquals(ToolsHelperState.RUNNING, ctx.state)
        assertEquals(0, ctx.errors.size)
    }

    @Test
    fun shortString() = runTest {
        val ctx = ToolsHelperContext(
            state = ToolsHelperState.RUNNING,
            orderFilterValidating = ToolsHelperOrderFilter(searchString = "12")
        )
        chain.exec(ctx)
        assertEquals(ToolsHelperState.FAILING, ctx.state)
        assertEquals(1, ctx.errors.size)
        assertEquals("validation-searchString-tooShort", ctx.errors.first().code)
    }

    @Test
    fun normalString() = runTest {
        val ctx = ToolsHelperContext(
            state = ToolsHelperState.RUNNING,
            orderFilterValidating = ToolsHelperOrderFilter(searchString = "12345")
        )
        chain.exec(ctx)
        assertEquals(ToolsHelperState.RUNNING, ctx.state)
        assertEquals(0, ctx.errors.size)
    }

    @Test
    fun longString() = runTest {
        val ctx = ToolsHelperContext(
            state = ToolsHelperState.RUNNING,
            orderFilterValidating = ToolsHelperOrderFilter(searchString = "12".repeat(51))
        )
        chain.exec(ctx)
        assertEquals(ToolsHelperState.FAILING, ctx.state)
        assertEquals(1, ctx.errors.size)
        assertEquals("validation-searchString-tooLong", ctx.errors.first().code)
    }

    companion object {
        val chain = rootChain {
            validateSearchStringLength("")
        }.build()
    }
}