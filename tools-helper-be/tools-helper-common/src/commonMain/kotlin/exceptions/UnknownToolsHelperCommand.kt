package exceptions

import models.ToolsHelperCommand


class UnknownToolsHelperCommand(command: ToolsHelperCommand) : Throwable("Wrong command $command at mapping toTransport stage")
