package no.nrk.nrk_kotlin.business.depedencyInjection

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context : Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}