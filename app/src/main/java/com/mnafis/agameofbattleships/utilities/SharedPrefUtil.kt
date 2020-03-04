package com.mnafis.agameofbattleships.utilities

import android.content.SharedPreferences
import androidx.annotation.StringDef
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefUtil @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun setString(@SharedPrefKey key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(@SharedPrefKey key: String): String {
        return sharedPreferences.getString(key, DEFAULT_VALUE)!!
    }

    fun setInt(@SharedPrefKey key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getInt(@SharedPrefKey key: String): Int {
        return sharedPreferences.getInt(key, -1)
    }

    @Retention(AnnotationRetention.SOURCE)
    @StringDef(MUSIC_STATUS, SOUND_STATUS)
    annotation class SharedPrefKey

    companion object{
        const val MUSIC_STATUS = "musicStatus"
        const val SOUND_STATUS = "soundStatus"
        const val DEFAULT_VALUE = "defaultValue"
    }
}