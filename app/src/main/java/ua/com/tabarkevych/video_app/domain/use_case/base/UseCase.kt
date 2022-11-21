package ua.com.tabarkevych.video_app.domain.use_case.base

abstract class UseCase<in P : EmptyParams, out R : Any> {

    abstract suspend fun run(params: P): Result<R>

    suspend fun execute(params: P): Result<R> {
        return run(params)
    }
}