package com.kagemora.mediaflow.core.datastore

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface DataStoreModule {

    @Binds
    @Singleton
    fun bindsTokenStorage(impl: TokenStorageImpl): TokenStorage

    companion object {
        @Provides
        @Singleton
        fun provideDataStore(
            application: Application
        ): DataStore<Preferences> {
            return PreferenceDataStoreFactory.create(
                produceFile = { application.preferencesDataStoreFile("auth_prefs") }
            )
        }
    }
}