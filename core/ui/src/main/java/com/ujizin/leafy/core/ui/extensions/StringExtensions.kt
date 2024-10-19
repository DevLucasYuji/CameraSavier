package com.ujizin.leafy.core.ui.extensions

val String.Companion.Empty
    get() = ""

fun String.capitalize() = replaceFirstChar {
    if (it.isLowerCase()) it.titlecase() else it.toString()
}
