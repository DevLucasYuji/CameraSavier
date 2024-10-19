package com.ujizin.leafy.domain.result

import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onStart

fun <T> Flow<T>.asResult(): Flow<Result<T>> =
    map<T, Result<T>> { Result.Success(it) }
        .onStart { emit(Result.Loading) }
        .catch { emit(Result.Error(it)) }

fun <T : Any> Flow<Result<T?>>.mapResult(): Flow<T> = mapNotNull { result ->
    when (result) {
        is Result.Error -> {
            result.exception?.let(FirebaseCrashlytics.getInstance()::recordException)
            null
        }
        Result.Loading -> null
        is Result.Success -> result.data
    }
}
