package ru.patseev.helper.cor.handlers

import ru.patseev.helper.cor.ICorChainDsl
import ru.patseev.helper.cor.ICorExec
import ru.patseev.helper.cor.ICorExecDsl

class CorChain<T>(
    title: String = "",
    description: String = "",
    blockOn: suspend T.() -> Boolean = { true },
    blockExcept: suspend T.(e: Throwable) -> Unit,
    private val execs: List<ICorExec<T>>
) : AbstractICorExec<T>(title, description, blockOn, blockExcept) {
    override suspend fun handle(context: T) {
        execs.forEach { worker ->
            worker.exec(context)
        }
    }
}


class CorChainDsl<T> : ICorChainDsl<T>, CorExecDsl<T>() {
    override var description: String = ""
    override var title: String = ""

    private val execs = mutableListOf<ICorExecDsl<T>>()
    override fun add(worker: ICorExecDsl<T>) {
        execs.add(worker)
    }

    override fun build(): ICorExec<T> = CorChain(
        title = title,
        description = description,
        blockOn = blockOn,
        execs = execs.map{ it.build() },
        blockExcept = blockExcept
    )
}