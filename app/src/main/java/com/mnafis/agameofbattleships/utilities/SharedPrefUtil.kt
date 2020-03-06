package com.mnafis.agameofbattleships.utilities

import android.content.SharedPreferences
import androidx.annotation.StringDef
import com.mnafis.agameofbattleships.utilities.AudioStatus.OFF
import com.mnafis.agameofbattleships.utilities.AudioStatus.ON
import com.mnafis.agameofbattleships.utilities.GameDifficulty.EASY
import com.mnafis.agameofbattleships.utilities.GameDifficulty.NORMAL
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPrefUtil @Inject constructor(private val sharedPreferences: SharedPreferences) {

    @Retention(AnnotationRetention.SOURCE)
    @StringDef(MUSIC_STATUS, SOUND_STATUS)
    annotation class SharedPrefKey

    companion object {
        const val DEFAULT_VALUE = "defaultValue"
        const val MUSIC_STATUS = "musicStatus"
        const val SOUND_STATUS = "soundStatus"
        const val GAME_DIFFICULTY_LEVEL = "gameDifficultyLevel"
    }

    fun setMusicStatus(status: AudioStatus) = setBoolean(MUSIC_STATUS, status.value)
    fun getMusicStatus(defaultStatus: AudioStatus = ON): AudioStatus =
        if (getBoolean(MUSIC_STATUS, defaultStatus.value)) ON else OFF

    fun setSoundStatus(status: AudioStatus) = setBoolean(SOUND_STATUS, status.value)
    fun getSoundStatus(defaultStatus: AudioStatus = ON): AudioStatus =
        if (getBoolean(SOUND_STATUS, defaultStatus.value)) ON else OFF

    fun setGameDifficultyLevel(gameDifficulty: GameDifficulty) = setString(GAME_DIFFICULTY_LEVEL, gameDifficulty.value)
    fun getGameDifficultyLevel(defaultGameDifficulty: GameDifficulty = NORMAL): GameDifficulty =
        if (getString(GAME_DIFFICULTY_LEVEL, NORMAL.value) == defaultGameDifficulty.value) NORMAL else EASY

    private fun setString(@SharedPrefKey key: String, value: String) =
        sharedPreferences.edit().putString(key, value).apply()
    private fun getString(@SharedPrefKey key: String, defaultValue: String = DEFAULT_VALUE): String =
        sharedPreferences.getString(key, defaultValue)!!

    private fun setInt(@SharedPrefKey key: String, value: Int) =
        sharedPreferences.edit().putInt(key, value).apply()
    private fun getInt(@SharedPrefKey key: String, defaultValue: Int = -1): Int =
        sharedPreferences.getInt(key, defaultValue)

    private fun setBoolean(@SharedPrefKey key: String, value: Boolean) =
        sharedPreferences.edit().putBoolean(key, value).apply()
    private fun getBoolean(@SharedPrefKey key: String, defaultValue: Boolean = false): Boolean =
        sharedPreferences.getBoolean(key, defaultValue)
}