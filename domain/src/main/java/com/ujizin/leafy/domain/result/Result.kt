package com.ujizin.leafy.domain.result

/** Result wrapper to use cases. */
sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>

    data class Error(val exception: Throwable? = null) : Result<Nothing>

    data object Loading : Result<Nothing>
}
