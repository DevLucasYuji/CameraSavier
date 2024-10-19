package com.ujizin.leafy.core.navigation

import android.net.Uri
import androidx.core.net.toUri
import kotlinx.serialization.Serializable

sealed interface Destination {

    @Serializable data object Home : Destination

    @Serializable data class Search(val autoFocus: Boolean = false) : Destination

    @Serializable data object Camera : Destination

    @Serializable data object Tasks : Destination

    @Serializable data object Preferences : Destination

    @Serializable data object Publish : Destination

    @Serializable data object Alarm : Destination

    @Serializable data object About : Destination

    @Serializable
    data class PlantDetails(val plantId: Long) : Destination {
        companion object {
            fun createDeeplink(plantId: Long): Uri = "$DEEPLINK_URL/$plantId".toUri()

            const val DEEPLINK_URL = "$BASE_PATH/plant"
        }
    }

    @Serializable data class PlantEdit(val plantId: Long) : Destination

    companion object {
        const val BASE_PATH = "app://leafy"
    }
}
