import helper.asToolsHelperError
import kotlinx.datetime.Clock
import models.ToolsHelperCommand
import models.ToolsHelperState
import kotlin.reflect.KClass

suspend inline fun <T> IToolsHelperAppSettings.controllerHelper(
    crossinline getRequest: suspend ToolsHelperContext.() -> Unit,
    crossinline toResponse: suspend ToolsHelperContext.() -> T,
    clazz: KClass<*>,
    logId: String,
): T {
    val logger = corSettings.loggerProvider.logger(clazz)
    val ctx = ToolsHelperContext(
        timeStart = Clock.System.now(),
    )
    return try {
        ctx.getRequest()
        logger.info(
            msg = "Request $logId started for ${clazz.simpleName}",
            marker = "BIZ",
            data = ctx.toLog(logId)
        )
        processor.exec(ctx)
        logger.info(
            msg = "Request $logId processed for ${clazz.simpleName}",
            marker = "BIZ",
            data = ctx.toLog(logId)
        )
        ctx.toResponse()
    } catch (e: Throwable) {
        logger.error(
            msg = "Request $logId failed for ${clazz.simpleName}",
            marker = "BIZ",
            data = ctx.toLog(logId)
        )
        ctx.state = ToolsHelperState.FAILING
        ctx.errors.add(e.asToolsHelperError())
        processor.exec(ctx)
        if (ctx.command == ToolsHelperCommand.NONE) {
            ctx.command = ToolsHelperCommand.READ
        }
        ctx.toResponse()
    }
}
