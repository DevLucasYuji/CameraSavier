package com.ujizin.leafy.core.local.converter

import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/** Database converter. */
internal class Converter {

    private val json: Json = Json

    @TypeConverter fun listStringToJson(list: List<String>) = json.encodeToString(list)

    @TypeConverter fun jsonToListString(value: String): List<String> = json.decodeFromString(value)
}
