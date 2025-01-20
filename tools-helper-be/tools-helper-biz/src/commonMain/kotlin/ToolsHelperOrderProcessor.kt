import general.initStatus
import general.operation
import general.stubs
import models.ToolsHelperCommand
import ru.patseev.helper.cor.rootChain
import stubs.*


@Suppress("unused")
class ToolsHelperOrderProcessor(private val corSettings: ToolsHelperCorSettings = ToolsHelperCorSettings.NONE) {
    suspend fun exec(ctx: ToolsHelperContext) = businessChain.exec(ctx.also { it.corSettings = corSettings })

    private val businessChain = rootChain<ToolsHelperContext> {
        initStatus("Инициализация статуса")

        operation("Создание заказа", ToolsHelperCommand.CREATE) {
            stubs("Обработка заглушек(стабов)") {
                stubCreateSuccess("Имитация успешной обработки", corSettings)
                stubValidationBadPartsCount("Имитация ошибки количества деталей")
                stubDbError("Имитация ошибки работы с БД")
                stubNoCase("Ошибка: запрошенный стаб недопустим")
            }
        }

        operation("Чтение заказа", ToolsHelperCommand.READ) {
            stubs("Обработка заглушек(стабов)") {
                stubReadSuccess("Имитация успешной обработки", corSettings)
                stubValidationBadId("Имитация ошибки ид")
                stubDbError("Имитация ошибки работы с БД")
                stubNoCase("Ошибка: запрошенный стаб недопустим")
            }
        }

        operation("Обновление заказа", ToolsHelperCommand.UPDATE) {
            stubs("Обработка заглушек(стабов)") {
                stubUpdateSuccess("Имитация успешной обработки", corSettings)
                stubValidationBadId("Имитация ошибки ид")
                stubValidationBadPartsCount("Имитация ошибки количества деталей")
                stubDbError("Имитация ошибки работы с БД")
                stubNoCase("Ошибка: запрошенный стаб недопустим")
            }
        }

        operation("Удаление заказа", ToolsHelperCommand.DELETE) {
            stubs("Обработка заглушек(стабов)") {
                stubDeleteSuccess("Имитация успешной обработки", corSettings)
                stubValidationBadId("Имитация ошибки ид")
                stubValidationBadPartsCount("Имитация ошибки количества деталей")
                stubDbError("Имитация ошибки работы с БД")
                stubNoCase("Ошибка: запрошенный стаб недопустим")
            }
        }

        operation("Поиск заказов", ToolsHelperCommand.SEARCH) {
            stubs("Обработка заглушек(стабов)") {
                stubSearchSuccess("Имитация успешной обработки", corSettings)
                stubValidationBadId("Имитация ошибки ид")
                stubValidationBadPartsCount("Имитация ошибки количества деталей")
                stubDbError("Имитация ошибки работы с БД")
                stubNoCase("Ошибка: запрошенный стаб недопустим")
            }
        }
    }.build()
}
