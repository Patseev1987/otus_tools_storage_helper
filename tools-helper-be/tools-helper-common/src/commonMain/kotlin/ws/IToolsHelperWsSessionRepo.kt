package ws

interface IToolsHelperWsSessionRepo {
    fun add(session: IToolsHelperWsSession)
    fun clearAll()
    fun remove(session: IToolsHelperWsSession)
    suspend fun <K> sendAll(obj: K)

    companion object {
        val NONE = object : IToolsHelperWsSessionRepo {
            override fun add(session: IToolsHelperWsSession) {}
            override fun clearAll() {}
            override fun remove(session: IToolsHelperWsSession) {}
            override suspend fun <K> sendAll(obj: K) {}
        }
    }
}