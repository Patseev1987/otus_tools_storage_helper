package exception

import models.ToolsHelperWorkMode

class ToolsHelperOrderDbNotConfiguredException (val workMode: ToolsHelperWorkMode): Exception(
    "Database is not configured properly for workmode $workMode"
)