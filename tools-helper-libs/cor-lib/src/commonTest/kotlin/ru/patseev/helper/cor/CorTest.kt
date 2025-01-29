package ru.patseev.helper.cor

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails


class CorTest {
    private suspend fun execute(dsl: ICorExecDsl<TestContext>): TestContext {
        val ctx = TestContext()
        dsl.build().exec(ctx)
        return ctx
    }

    @Test
    fun `handle should execute`() = runTest {
        val chain = rootChain<TestContext> {
            worker {
                handle { info += "worker 1; " }
            }
        }
        val ctx = execute(chain)
        assertEquals("worker 1; ", ctx.info)
    }

    @Test
    fun `on should check condition`() = runTest {
        val chain = rootChain<TestContext> {
            worker {
                on { status == TestContext.CorStatus.ERROR }
                handle { info += "worker 1; " }
            }
            worker {
                on { status == TestContext.CorStatus.NONE }
                handle {
                    info += "worker 2; "
                    status = TestContext.CorStatus.FAILING
                }
            }
            worker {
                on { status == TestContext.CorStatus.FAILING }
                handle { info += "worker 3; " }
            }
        }
        assertEquals("worker 2; worker 3; ", execute(chain).info)
    }

    @Test
    fun `except should execute when exception`() = runTest {
        val chain = rootChain<TestContext> {
            worker {
                handle { throw RuntimeException("Alarm!") }
                except { info += it.message }
            }
        }
        assertEquals("Alarm!", execute(chain).info)
    }

    @Test
    fun `should throw when exception and no except`() = runTest {
        val chain = rootChain<TestContext> {
            worker("throw") { throw RuntimeException("Alarm!") }
        }
        assertFails {
            execute(chain)
        }
    }

    @Test
    fun `complex chain example`() = runTest {
        val chain = rootChain<TestContext> {
            worker {
                title = "Инициализация статуса"
                description = "При старте обработки цепочки, статус еще не установлен. Проверяем его"

                on { status == TestContext.CorStatus.NONE }
                handle { status = TestContext.CorStatus.RUNNING }
                except { status = TestContext.CorStatus.ERROR }
            }

            chain {
                on { status == TestContext.CorStatus.RUNNING }

                worker(
                    title = "Лямбда обработчик",
                    description = "Пример использования обработчика в виде лямбды"
                ) {
                    anyNumber += 4
                }
            }

            printResult()

        }.build()

        val ctx = TestContext()
        chain.exec(ctx)
        println("Тестовый контекст: $ctx")
    }

    private fun ICorChainDsl<TestContext>.printResult() = worker(title = "Печатаем пример") {
        println("anyNumber = $anyNumber")
    }
}