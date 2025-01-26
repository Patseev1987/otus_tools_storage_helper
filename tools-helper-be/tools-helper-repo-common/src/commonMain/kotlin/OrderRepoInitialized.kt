import models.ToolsHelperOrder

class OrderRepoInitialized (
    private val repo: IRepoOrderInitializable,
    initObjects: Collection<ToolsHelperOrder> = emptyList()
): IRepoOrderInitializable by repo {
    val initObjects: List<ToolsHelperOrder> = save(initObjects).toList()
}

