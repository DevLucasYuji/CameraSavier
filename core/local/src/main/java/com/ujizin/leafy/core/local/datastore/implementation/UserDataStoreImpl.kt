package com.ujizin.leafy.core.local.datastore.implementation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ujizin.leafy.core.local.datastore.UserDataStore
import com.ujizin.leafy.core.local.model.UserStore
import com.ujizin.leafy.core.local.model.orDefault
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal class UserDataStoreImpl(context: Context, private val serializer: Json) : UserDataStore {

    private val Context.userStore by preferencesDataStore(name = USER_PREFERENCES_NAME)

    private val userStore: DataStore<Preferences> = context.userStore

    private val userKey = stringPreferencesKey("user_stringify")

    private suspend fun createUser(user: UserStore) {
        userStore.edit { preferences -> preferences[userKey] = Json.encodeToString(user) }
    }

    override fun getUser(): Flow<UserStore> =
        userStore.data.map { preferences ->
            val userStore =
                preferences[userKey]
                    ?: run {
                        return@map UserStore.default().also { createUser(it) }
                    }

            serializer.decodeFromString(userStore)
        }

    override suspend fun updateUser(user: UserStore) {
        userStore.edit { preferences ->
            preferences[userKey] =
                serializer.encodeToString(
                    value =
                        getUser()
                            .firstOrNull()
                            .orDefault()
                            .copy(
                                nickname = user.nickname,
                                theme = user.theme,
                                language = user.language,
                                dynamicColor = user.dynamicColor,
                            )
                )
        }
    }

    companion object {
        private const val USER_PREFERENCES_NAME = "user"
    }
}
