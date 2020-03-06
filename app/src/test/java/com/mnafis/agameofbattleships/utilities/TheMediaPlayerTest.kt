package com.mnafis.agameofbattleships.utilities

import android.media.MediaPlayer
import com.mnafis.agameofbattleships.utilities.AudioStatus.OFF
import com.mnafis.agameofbattleships.utilities.AudioStatus.ON
import io.mockk.every
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
    fun updateSoundStatus_updatesSoundStatusAndSharedPref() {
        subject.updateSoundStatus(OFF)
        subject.playMenuSelectionEffect()

        verify { sharedPrefUtil.setSoundStatus(OFF) }
        verify (exactly = 0) { soundPlayer.start() }
    }

    @Test
    fun updateMusicStatus_updatesMusicStatusValueAndSharedPref() {
        subject.updateMusicStatus(OFF)
        subject.playMusic()

        verify { sharedPrefUtil.setMusicStatus(OFF) }
        verify (exactly = 0) { mediaPlayer.start() }
    }

    @Test
    fun playMenuSelectionEffect_soundStatusTrue_setsVolumeAndStartsSound() {
        subject.playMenuSelectionEffect()

        verify {
            soundPlayer.setVolume(0.10f, 0.10f)
            soundPlayer.start()
        }
    }

    @Test
    fun playMenuSelectionEffect_soundStatusFalse_DoesNothing() {
        subject.updateSoundStatus(OFF)

        subject.playMenuSelectionEffect()

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
        subject.updateMusicStatus(OFF)

        subject.pauseMusic()

        verify (exactly = 0) { mediaPlayer.pause() }
    }

    @Test
    fun stopMusic_musicPlaying_stopsMusicAndPrepares() {
        every { mediaPlayer.isPlaying } returns true

        subject.stopMusic()

        verify {
            mediaPlayer.stop()
            mediaPlayer.prepare()
        }
    }

    @Test
    fun stopMusic_musicNotPlaying_doesNothing() {
        every { mediaPlayer.isPlaying } returns false

        subject.stopMusic()

        verify (exactly = 0) {
            mediaPlayer.stop()
            mediaPlayer.prepare()
        }
    }

    @Test
    fun playMusic_musicStatusTrue_startsMusic() {
        subject.updateMusicStatus(ON)

        subject.playMusic()

        verify { mediaPlayer.start() }
    }

    @Test
    fun playMusic_musicStatusFalse_doesNothing() {
        subject.updateMusicStatus(OFF)

        subject.playMusic()

        verify (exactly = 0) { mediaPlayer.start() }
    }
}