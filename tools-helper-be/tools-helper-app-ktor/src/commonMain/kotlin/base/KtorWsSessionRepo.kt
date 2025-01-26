package base

import ws.IToolsHelperWsSession
import ws.IToolsHelperWsSessionRepo


class KtorWsSessionRepo: IToolsHelperWsSessionRepo {
    private val sessions: MutableSet<IToolsHelperWsSession> = mutableSetOf()
    override fun add(session: IToolsHelperWsSession) {
        sessions.add(session)
    }

    override fun clearAll() {
        sessions.clear()
    }

    override fun remove(session: IToolsHelperWsSession) {
        sessions.remove(session)
    }

    override suspend fun <T> sendAll(obj: T) {
        sessions.forEach { it.send(obj) }
    }
}
