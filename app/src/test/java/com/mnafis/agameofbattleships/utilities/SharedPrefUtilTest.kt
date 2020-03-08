package com.mnafis.agameofbattleships.utilities

import android.content.SharedPreferences
import com.mnafis.agameofbattleships.utilities.AudioStatus.OFF
import com.mnafis.agameofbattleships.utilities.AudioStatus.ON
import com.mnafis.agameofbattleships.utilities.GameDifficulty.EASY
import com.mnafis.agameofbattleships.utilities.GameDifficulty.NORMAL
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.DEFAULT_VALUE
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.GAME_DIFFICULTY_LEVEL
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.MUSIC_STATUS
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.SOUND_STATUS
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SharedPrefUtilTest {

    private val sharedPreferences: SharedPreferences = mockk(relaxed = true)

    private lateinit var subject: SharedPrefUtil

    @Before
    fun setUp() {
        subject = SharedPrefUtil(sharedPreferences)
    }

    @Test
    fun setMusicStatus_addsMusicStatusValueToSharedPref() {
        subject.setMusicStatus(ON)

        verify { sharedPreferences.edit().putBoolean(MUSIC_STATUS, ON.value).apply() }
    }

    @Test
    fun getMusicStatus_returnsValue() {
        every { sharedPreferences.getBoolean(MUSIC_STATUS, OFF.value) } returns ON.value

        assertEquals(ON, subject.getMusicStatus(OFF))
    }

    @Test
    fun setSoundStatus_addsSoundStatusValueToSharedPref() {
        subject.setSoundStatus(ON)

        verify { sharedPreferences.edit().putBoolean(SOUND_STATUS, ON.value).apply() }
    }

    @Test
    fun getSoundStatus_returnsValue() {
        every { sharedPreferences.getBoolean(SOUND_STATUS, OFF.value) } returns ON.value

        assertEquals(ON, subject.getSoundStatus(OFF))
    }

    @Test
    fun setGameDifficultyLevel_addsGameDifficultyLevelValueToSharedPref() {
        subject.setGameDifficultyLevel(EASY)

        verify { sharedPreferences.edit().putString(GAME_DIFFICULTY_LEVEL, EASY.value).apply() }
    }

    @Test
    fun getGameDifficultyLevel_returnsValue() {
        every { sharedPreferences.getString(GAME_DIFFICULTY_LEVEL, NORMAL.value) } returns EASY.value

        assertEquals(EASY, subject.getGameDifficultyLevel(NORMAL))
    }
}