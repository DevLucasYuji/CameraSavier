package com.ujizin.leafy.core.ui.extensions

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.launchCatching(
    exceptionBlock: (CoroutineContext, Throwable) -> Unit,
    block: suspend CoroutineScope.() -> Unit,
) = launch(CoroutineExceptionHandler(exceptionBlock), block = block)
