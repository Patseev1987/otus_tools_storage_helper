package ws

interface IToolsHelperWsSession {
    suspend fun <T> send(obj: T)
    companion object {
        val NONE = object : IToolsHelperWsSession {
            override suspend fun <T> send(obj: T) {
            }
        }
    }
}
