package ru.patseev.helper.cor

import kotlinx.coroutines.test.runTest
import ru.patseev.helper.cor.handlers.CorWorker
import kotlin.test.Test
import kotlin.test.assertEquals

class CorWorkerText {

    @Test
    fun `worker should execute handle`() = runTest {
        val worker = CorWorker<TestContext>(
            title = "worker 1",
            blockHandel = {info += "worker 1; "}
        )

        val testContext = TestContext()
        worker.exec(testContext)
        assertEquals( "worker 1; ", testContext.info)
    }

    @Test
    fun `worker should not execute handle`() = runTest {
        val worker = CorWorker<TestContext>(
            title = "worker 2",
            blockOn = {status == TestContext.CorStatus.FAILING},
            blockHandel = {info += "worker 2; "}
        )

        val testContext = TestContext()
        worker.exec(testContext)
        assertEquals( "", testContext.info)
    }

    @Test
    fun `worker should handle exception`() = runTest {
        val worker = CorWorker<TestContext>(
            title = "worker 3",
            blockOn = {status == TestContext.CorStatus.NONE},
            blockHandel = {throw RuntimeException("Alarm!")},
            blockExcept = {exception -> info += exception.message}
        )

        val testContext = TestContext()
        worker.exec(testContext)
        assertEquals( "Alarm!", testContext.info)
    }
}