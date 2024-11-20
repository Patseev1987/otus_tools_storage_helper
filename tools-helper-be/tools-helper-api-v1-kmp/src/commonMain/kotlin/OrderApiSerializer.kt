@file:Suppress("UNCHECKED_CAST")

import kotlinx.serialization.json.Json
import ru.patseev.helper.api.models.IRequest
import ru.patseev.helper.api.models.IResponse

val apiMapper = Json { ignoreUnknownKeys = true }

fun <T : IRequest> apiRequestDeserialize(json: String) = apiMapper.decodeFromString<IRequest>(json) as T

fun apiResponseSerialize(obj: IResponse): String = apiMapper.encodeToString(IResponse.serializer(), obj)

fun <T : IResponse> apiResponseDeserialize(json: String) = apiMapper.decodeFromString<IResponse>(json) as T

fun apiRequestSerialize(obj: IRequest): String = apiMapper.encodeToString(IRequest.serializer(), obj)