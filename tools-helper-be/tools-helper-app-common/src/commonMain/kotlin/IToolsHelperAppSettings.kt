import ru.patseev.toolsHelper.biz.ToolsHelperOrderProcessor

interface IToolsHelperAppSettings {
    val processor: ToolsHelperOrderProcessor
    val corSettings: ToolsHelperCorSettings
}
