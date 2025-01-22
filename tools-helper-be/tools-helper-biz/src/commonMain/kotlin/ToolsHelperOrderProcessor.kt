import general.initStatus
import general.operation
import general.stubs
import models.ToolsHelperCommand
import models.ToolsHelperEmployeeId
import models.ToolsHelperOperationId
import models.ToolsHelperOrderId
import ru.patseev.helper.cor.rootChain
import ru.patseev.helper.cor.worker
import stubs.*
import validation.*


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

            validation {
                worker("Копируем поля в adValidating") { orderValidating = orderRequest.deepCopy() }
                worker("Очистка id") { orderValidating.id = ToolsHelperOrderId.NONE }
                worker("Очистка operationId") {
                    orderValidating.operationId = ToolsHelperOperationId(orderValidating.operationId.asString().trim())
                }
                worker("Очистка ownerId") {
                    orderValidating.ownerId = ToolsHelperEmployeeId(orderValidating.ownerId.asString().trim())
                }
                worker("Очистка tools") { orderValidating.tools = mutableMapOf() }
                worker("Очистка missingTools") { orderValidating.missingTools = mutableMapOf() }
                validateIds("Проверка полей с ид")
                validateOrderStatus("Проверка статуса заказа")
                validatePartCount("Проверка количества деталей")
                validateCreateDate("Проверяем дату создания")
                finishAdValidation("Завершение проверок")
            }
        }

        operation("Чтение заказа", ToolsHelperCommand.READ) {
            stubs("Обработка заглушек(стабов)") {
                stubReadSuccess("Имитация успешной обработки", corSettings)
                stubValidationBadId("Имитация ошибки ид")
                stubDbError("Имитация ошибки работы с БД")
                stubNoCase("Ошибка: запрошенный стаб недопустим")
            }

            validation {
                worker("Копируем поля в adValidating") { orderValidating = orderRequest.deepCopy() }
                worker("Очистка id") { orderValidating.id = ToolsHelperOrderId(orderValidating.id.asString().trim()) }
                validateIdNotEmpty("Проверка, ид не пустое")
                validateIdProperFormat("Проверка корректного заполнения ид")
                finishAdValidation("Завершение проверок")
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

            validation {
                worker("Копируем поля в adValidating") { orderValidating = orderRequest.deepCopy() }
                worker("Очистка id") { orderValidating.id = ToolsHelperOrderId(orderValidating.id.asString().trim()) }
                validateIdNotEmpty("Проверка, ид не пустое")
                validateIdProperFormat("Проверка корректного заполнения ид")
                worker("Очистка operationId") {
                    orderValidating.operationId = ToolsHelperOperationId(orderValidating.operationId.asString().trim())
                }
                worker("Очистка ownerId") {
                    orderValidating.ownerId = ToolsHelperEmployeeId(orderValidating.ownerId.asString().trim())
                }
                worker("Очистка tools") { orderValidating.tools = mutableMapOf() }
                worker("Очистка missingTools") { orderValidating.missingTools = mutableMapOf() }
                validateIds("Проверка полей с ид")
                validateOrderStatus("Проверка статуса заказа")
                validatePartCount("Проверка количества деталей")
                validateCreateDate("Проверяем дату создания")
                finishAdValidation("Завершение проверок")
            }

        }

        operation("Удаление заказа", ToolsHelperCommand.DELETE)
        {
            stubs("Обработка заглушек(стабов)") {
                stubDeleteSuccess("Имитация успешной обработки", corSettings)
                stubValidationBadId("Имитация ошибки ид")
                stubValidationBadPartsCount("Имитация ошибки количества деталей")
                stubDbError("Имитация ошибки работы с БД")
                stubNoCase("Ошибка: запрошенный стаб недопустим")
            }

            validation {
                worker("Копируем поля в adValidating") { orderValidating = orderRequest.deepCopy() }
                worker("Очистка id") { orderValidating.id = ToolsHelperOrderId(orderValidating.id.asString().trim()) }
                validateIdNotEmpty("Проверка, ид не пустое")
                validateIdProperFormat("Проверка корректного заполнения ид")
                validateLockNotEmpty("Проверка на непустой lock")
                validateLockProperFormat("Проверка формата lock")
                finishAdValidation("Завершение проверок")
            }

        }

        operation("Поиск заказов", ToolsHelperCommand.SEARCH)
        {
            stubs("Обработка заглушек(стабов)") {
                stubSearchSuccess("Имитация успешной обработки", corSettings)
                stubValidationBadId("Имитация ошибки ид")
                stubValidationBadPartsCount("Имитация ошибки количества деталей")
                stubDbError("Имитация ошибки работы с БД")
                stubNoCase("Ошибка: запрошенный стаб недопустим")
            }

            validation {
                worker("Копируем поля в adValidating") { orderFilterValidating = orderFilterRequest.copy() }
                validateSearchStringLength("Проверка строки поиска")
                finishAdFilterValidation("Завершение проверок")
            }
        }
    }.build()
}
