package ua.com.tabarkevych.video_app.extensions

import kotlinx.coroutines.flow.*
import ua.com.tabarkevych.video_app.domain.error.Error
import ua.com.tabarkevych.video_app.domain.use_case.base.Result
import java.util.concurrent.CancellationException

private const val TAG = "RESULT"

suspend fun <T : Any> result(block: suspend () -> T): Result<T> =
    try {
        Result.Success(block())
    } catch (tr: Throwable) {
        handleError(tr)
    }

fun <T, R : Any> Flow<T>.transformToMappedResult(transform: (value: T) -> R): Flow<Result<R>> =
    transform {
        try {
            emit(Result.Success(transform(it)))
        } catch (tr: Throwable) {
            emit(handleError(tr))
        }
    }

fun <T : Any> Flow<T>.transformToResult(transform: (value: T) -> T = { it }): Flow<Result<T>> =
    transform {
        try {
            emit(Result.Success(transform(it)))
        } catch (tr: Throwable) {
            emit(handleError(tr))
        }
    }

fun <T : Any> resultFlow(block: suspend FlowCollector<Result<T>>.() -> Unit): Flow<Result<T>> =
    flow {
        try {
            block.invoke(this)
        } catch (tr: Throwable) {
            emit(handleError(tr))
        }
    }

suspend fun <T : Any> FlowCollector<Result<T>>.emitAllAsResult(flow: Flow<T>) {
    emitAll(flow.map { Result.Success(it) })
}

private fun handleError(tr: Throwable): Result.Error {
    when (tr) {
        is CancellationException -> throw tr
        is ua.com.tabarkevych.video_app.data.error.exeptions.Exception.NetworkRequestException -> loge(TAG, tr.error.message, tr)
        is ua.com.tabarkevych.video_app.data.error.exeptions.Exception.LocalRequestException -> {
            loge(TAG, tr.originalThrowable?.message, tr.originalThrowable)
        }
        else -> loge(TAG, tr.message, tr)
    }
    return Result.Error(Error.SomethingWentWrongError(tr.message?:"Error"))
}