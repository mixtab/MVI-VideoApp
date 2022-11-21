package ua.com.tabarkevych.video_app.domain.use_case.base

import kotlinx.coroutines.flow.Flow

abstract class FlowUseCase<in P : EmptyParams, out R : Any> {

    abstract fun run(params: P): Flow<Result<R>>

    fun execute(params: P): Flow<Result<R>> {
        return run(params)
    }
}