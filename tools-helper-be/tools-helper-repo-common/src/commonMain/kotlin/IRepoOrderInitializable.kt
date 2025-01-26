import models.ToolsHelperOrder
import repo.IRepoOrder

interface IRepoOrderInitializable: IRepoOrder {
    fun save(orders: Collection<ToolsHelperOrder>): Collection<ToolsHelperOrder>
}