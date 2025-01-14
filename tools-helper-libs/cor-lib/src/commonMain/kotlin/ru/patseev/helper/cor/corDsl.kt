package ru.patseev.helper.cor

import ru.patseev.helper.cor.handlers.CorChainDsl
import ru.patseev.helper.cor.handlers.CorWorkerDsl


@CorDslMarker
interface ICorExecDsl<T> {
    var title: String
    var description: String
    fun on(function: suspend T.() -> Boolean)
    fun except(function: suspend T.(e: Throwable) -> Unit)

    fun build(): ICorExec<T>
}

@CorDslMarker
interface ICorWorkerDsl<T> : ICorExecDsl<T> {
    fun handle(function: suspend T.() -> Unit)
}

@CorDslMarker
interface ICorChainDsl<T> : ICorExecDsl<T> {
    fun add(worker: ICorExecDsl<T>)
}

fun <T> rootChain(block: ICorChainDsl<T>.() -> Unit) = CorChainDsl<T>().apply(block)

fun <T> ICorChainDsl<T>.chain(block: ICorChainDsl<T>.() -> Unit) {
    add(CorChainDsl<T>().apply(block))
}

fun <T> ICorChainDsl<T>.worker(block: ICorWorkerDsl<T>.() -> Unit) {
    add(CorWorkerDsl<T>().apply(block))
}

fun <T> ICorChainDsl<T>.worker(
    title: String,
    description: String = "",
    blockHandel: T.() -> Unit
){
    add(CorWorkerDsl<T>().also {
        it.title = title
        it.description = description
        it.handle(blockHandel)
    })
}
