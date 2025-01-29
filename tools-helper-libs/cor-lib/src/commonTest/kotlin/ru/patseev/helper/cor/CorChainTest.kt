package ru.patseev.helper.cor

import kotlinx.coroutines.test.runTest
import ru.patseev.helper.cor.handlers.CorChain
import ru.patseev.helper.cor.handlers.CorWorker
import kotlin.test.Test
import kotlin.test.assertEquals

class CorChainTest {
    @Test
    fun `chain should execute workers`() = runTest {
        val createWorker = { title: String ->
            CorWorker<TestContext>(
                title = title,
                blockOn = { status == TestContext.CorStatus.NONE },
                blockHandel = { info += "$title; " }
            )
        }
        val chain = CorChain(
            execs = listOf(createWorker("worker 1"), createWorker("worker 2")),
            title = "chain",
        )
        val ctx = TestContext()
        chain.exec(ctx)
        assertEquals("worker 1; worker 2; ", ctx.info)
    }
}