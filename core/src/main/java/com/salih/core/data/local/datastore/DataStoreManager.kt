package com.salih.core.data.local.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager(private val context: Context) {

    private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

    val isLoggedInFlow: Flow<Boolean> = context.dataStore.data
        .map { prefs ->
            val loggedIn = prefs[IS_LOGGED_IN] ?: false
            loggedIn
        }

    suspend fun setLoginStatus(isLoggedIn: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_LOGGED_IN] = isLoggedIn
        }
    }

    suspend fun clearLoginStatus() {
        setLoginStatus(false)
    }
}