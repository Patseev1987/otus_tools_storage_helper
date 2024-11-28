package ru.patseev.helper.ktor

import IToolsHelperAppSettings
import ToolsHelperCorSettings
import ru.patseev.toolsHelper.biz.ToolsHelperOrderProcessor

data class ToolsHelperAppSettings(
    val appUrls: List<String> = emptyList(),
    override val corSettings: ToolsHelperCorSettings = ToolsHelperCorSettings(),
    override val processor: ToolsHelperOrderProcessor  = ToolsHelperOrderProcessor(corSettings),
) : IToolsHelperAppSettings