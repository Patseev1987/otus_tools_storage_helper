package repo

import helper.errorSystem

abstract class OrderRepoBase: IRepoOrder {

    protected suspend fun tryOrderMethod(block: suspend () -> IDbOrderResponse): IDbOrderResponse = try {
        block()
    } catch (e: Throwable){
        DbOrderResponseError(errorSystem("methodException", e = e))
    }

    protected suspend fun tryOrdersMethod(block: suspend () -> IDbOrderResponse) = try {
        block()
    } catch (e: Throwable){
        DbOrdersResponseError(errorSystem("methodException", e = e))
    }
}