package com.example.talktome.common.network.networkUtils

import com.google.gson.JsonParseException
import java.io.EOFException
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

suspend fun <T : Any> safeApiCall(
    call: suspend () -> T
): ResultApi<T> {
    return try {
        ResultApi.Success(call.invoke())
    } catch (exception: Exception) {
        handleException(exception)
    }
}

fun <T : Any> handleException(
    e: Exception,
): ResultApi<T> =
    when (e) {
        is SocketTimeoutException -> ResultApi.HttpError("Сервер не отвечает",
            HttpURLConnection.HTTP_GATEWAY_TIMEOUT
        )
        is SSLHandshakeException -> ResultApi.HttpError("Возникли проблемы с сертификатом",
            HttpURLConnection.HTTP_GATEWAY_TIMEOUT
        )
        is JsonParseException -> ResultApi.HttpError("Ошибка обработки запроса")
        is EOFException -> ResultApi.HttpError("Ошибка загрузки, попробуйте еще раз")
        is ConnectException,
        is UnknownHostException -> ResultApi.HttpError("Возникли проблемы с интернетом",
            HttpURLConnection.HTTP_GATEWAY_TIMEOUT
        )
        is IOException ->  ResultApi.HttpError(e.message, HttpURLConnection.HTTP_INTERNAL_ERROR)
        else -> ResultApi.HttpError("Ошибка : ${e.javaClass.simpleName} ${e.localizedMessage}")
    }
