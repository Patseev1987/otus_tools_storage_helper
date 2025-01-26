package repo.exceptions

import models.ToolsHelperOrder

open class RepoOrderException(
    val order: ToolsHelperOrder,
    msg: String,
): RepoException(msg)