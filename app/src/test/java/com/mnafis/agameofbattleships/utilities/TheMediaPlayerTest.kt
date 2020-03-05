package com.mnafis.agameofbattleships.utilities

import android.media.MediaPlayer
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.DEFAULT_VALUE
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.MUSIC_STATUS
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.SOUND_STATUS
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class TheMediaPlayerTest {

    private val sharedPrefUtil: SharedPrefUtil = mockk(relaxed = true)
    private val mediaPlayer: MediaPlayer = mockk(relaxed = true)
    private val soundPlayer: MediaPlayer = mockk(relaxed = true)

    private lateinit var subject: TheMediaPlayer

    @Before
    fun setUp() {
        subject = TheMediaPlayer(sharedPrefUtil, mediaPlayer, soundPlayer)
    }

    @Test
    fun updateSoundStatus_doesNotContainDefaultValue_updatesSoundStatusValueAndSharedPref() {
        subject.updateSoundStatus("Off")

        verify { sharedPrefUtil.setString(SOUND_STATUS, "Off") }
    }

    @Test
    fun updateSoundStatus_containsDefaultValue_doesNothing() {
        subject.updateSoundStatus(DEFAULT_VALUE)

        verify (exactly = 0) { sharedPrefUtil.setString(any(), any()) }
    }

    @Test
    fun updateMusicStatus_doesNotContainDefaultValue_updatesMusicStatusValueAndSharedPref() {
        subject.updateMusicStatus("Off")

        verify { sharedPrefUtil.setString(MUSIC_STATUS, "Off") }
    }

    @Test
    fun updateMusicStatus_containsDefaultValue_doesNothing() {
        subject.updateMusicStatus(DEFAULT_VALUE)

        verify (exactly = 0) { sharedPrefUtil.setString(any(), any()) }
    }

    @Test
    fun playHitMusic_soundStatusTrue_setsVolumeAndStartsSound() {
        subject.playHitMusic()

        verify {
            soundPlayer.setVolume(0.15f, 0.15f)
            soundPlayer.start()
        }
    }

    @Test
    fun playHitMusic_soundStatusFalse_DoesNothing() {
        subject.updateSoundStatus("Off")

        subject.playHitMusic()

        verify (exactly = 0) {
            soundPlayer.setVolume(any(),any())
            soundPlayer.start()
        }
    }

    @Test
    fun pauseMusic_musicStatusTrue_pausesMusic() {
        subject.pauseMusic()

        verify { mediaPlayer.pause() }
    }

    @Test
    fun pauseMusic_musicStatusFalse_doesNothing() {
        subject.updateMusicStatus("Off")

        subject.pauseMusic()

        verify (exactly = 0) { mediaPlayer.pause() }
    }

    @Test
    fun stopMusic_stopsMusicAndPrepares() {
        subject.stopMusic()

        verify {
            mediaPlayer.stop()
            mediaPlayer.prepare()
        }
    }

    @Test
    fun playMusic_musicStatusTrue_startsMusic() {
        subject.updateMusicStatus("On")

        subject.playMusic()

        verify { mediaPlayer.start() }
    }

    @Test
    fun playMusic_musicStatusFalse_doesNothing() {
        subject.updateMusicStatus("Off")

        subject.playMusic()

        verify (exactly = 0) { mediaPlayer.start() }
    }
}