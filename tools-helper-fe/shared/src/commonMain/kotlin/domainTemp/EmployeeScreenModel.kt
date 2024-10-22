package domainTemp

data class EmployeeScreenModel(
    val avatar: String,
    val name: String,
    val surname: String,
    val department: String,
    val shiftTasks: List<Task>
)


