package exceptions

class UnknownRequestClass(message: String) : RuntimeException("Class cannot be mapped to ToolsHelperContext\n$message")