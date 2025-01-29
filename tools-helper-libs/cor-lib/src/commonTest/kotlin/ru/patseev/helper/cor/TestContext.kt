package ru.patseev.helper.cor

data class TestContext(
    var status: CorStatus = CorStatus.NONE,
    var anyNumber: Int = 0,
    var info: String = "",
){
    enum class CorStatus {
        NONE,
        RUNNING,
        FAILING,
        ERROR
    }
}
