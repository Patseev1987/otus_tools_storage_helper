package validation

import ToolsHelperContext
import kotlinx.coroutines.test.runTest
import models.ToolsHelperCommand
import models.ToolsHelperOrderFilter
import models.ToolsHelperState
import models.ToolsHelperWorkMode
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class BizValidationSearchTest: BaseValidationTest() {
    override val command = ToolsHelperCommand.SEARCH

    @Test
    fun correctEmpty() = runTest {
        val ctx = ToolsHelperContext(
            command = command,
            state = ToolsHelperState.NONE,
            workMode = ToolsHelperWorkMode.TEST,
            orderFilterRequest = ToolsHelperOrderFilter()
        )
        processor.exec(ctx)
        assertEquals(0, ctx.errors.size)
        assertNotEquals(ToolsHelperState.FAILING, ctx.state)
    }
}