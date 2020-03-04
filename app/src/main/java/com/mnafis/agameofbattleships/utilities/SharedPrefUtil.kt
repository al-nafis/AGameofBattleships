package com.mnafis.agameofbattleships.utilities

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.StringDef
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class SharedPrefUtil @Inject constructor(@Named("ApplicationContext") context: Context) {
    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences("appPref", Context.MODE_PRIVATE)

    fun setString(@SharedPrefKey key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(@SharedPrefKey key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun setInt(@SharedPrefKey key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getInt(@SharedPrefKey key: String): Int? {
        return sharedPreferences.getInt(key, -1)
    }

    fun setBoolean(@SharedPrefKey key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(@SharedPrefKey key: String): Boolean? {
        return sharedPreferences.getBoolean(key, false)
    }

    @Retention(AnnotationRetention.SOURCE)
    @StringDef(MUSIC_STATUS, SOUND_STATUS)
    annotation class SharedPrefKey

    companion object{
        const val MUSIC_STATUS = "musicStatus"
        const val SOUND_STATUS = "soundStatus"
    }
}