package ru.patseev.helper.cor.handlers

import ru.patseev.helper.cor.ICorExec
import ru.patseev.helper.cor.ICorWorkerDsl

class CorWorker<T>(
    title: String = "",
    description: String = "",
    blockOn: suspend T.() -> Boolean = { true },
    blockExcept: suspend T.(e: Throwable) -> Unit,
    private val blockHandel: suspend T.() -> Unit
) : AbstractICorExec<T>(title, description, blockOn, blockExcept) {
    override suspend fun handle(context: T) = blockHandel(context)
}

class CoreWorkerDsl<T> : CorExecDsl<T>(), ICorWorkerDsl<T> {
    private var blockHandle: suspend T.() -> Unit = {}

    override fun handle(function: suspend T.() -> Unit) {
        blockHandle = function
    }

    override fun build(): ICorExec<T> = CorWorker(
        title = title,
        description = description,
        blockOn = blockOn,
        blockExcept = blockExcept,
        blockHandel = blockHandle
    )
}