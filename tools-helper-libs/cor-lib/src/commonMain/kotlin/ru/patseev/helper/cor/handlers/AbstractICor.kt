package ru.patseev.helper.cor.handlers

import ru.patseev.helper.cor.ICorExec
import ru.patseev.helper.cor.ICorExecDsl


abstract class AbstractICorExec<T>(
    override val title: String = "",
    override val description: String = "",
    private val blockOn: suspend T.() -> Boolean = { true },
    private val blockExcept: suspend T.(e: Throwable) -> Unit
) : ICorExec<T> {
    protected abstract suspend fun handle(context: T)

    private suspend fun on(context: T):Boolean = context.blockOn()
    private suspend fun except(context: T,e: Throwable) = context.blockExcept(e)

    override suspend fun exec(context: T) {
        if (on(context)){
            try {
                handle(context)
            }catch (e:Throwable){
                except(context, e)
            }
        }
    }
}

abstract class CorExecDsl<T>: ICorExecDsl<T>{

    override var description: String = ""
    override var title: String = ""

    protected var blockOn: suspend T.() -> Boolean = { true }
    protected var blockExcept: suspend T.(e: Throwable) -> Unit ={e: Throwable -> throw e}

    override fun except(function: suspend T.(e: Throwable) -> Unit) {
        blockExcept = function
    }

    override fun on(function: suspend T.() -> Boolean){
        blockOn = function
    }
}