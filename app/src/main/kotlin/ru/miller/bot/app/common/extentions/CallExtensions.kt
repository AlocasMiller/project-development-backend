package ru.miller.bot.app.common.extentions

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import ru.miller.bot.app.common.validation.Validated
import ru.miller.bot.app.api.common.ErrorResponse

suspend inline fun <reified T : Validated<*>> ApplicationCall.receiveAndValidate(): T =
    this.receive<T>().also { it.validate() }

suspend inline fun <reified T : Any> ApplicationCall.respondSuccess(data: T) =
    this.respond(status = HttpStatusCode.OK, message = data)

suspend inline fun <reified T : Any> ApplicationCall.respondCreated(data: T) =
    this.respond(status = HttpStatusCode.Created, message = data)

suspend inline fun ApplicationCall.respondNoContent() =
    this.respond(status = HttpStatusCode.NoContent, message = Unit)

suspend fun ApplicationCall.respondError(status: HttpStatusCode, errorCode: String, message: String?) =
    this.respond(status = status, message = ErrorResponse(code = errorCode, message = message))

