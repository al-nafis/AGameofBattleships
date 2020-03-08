package com.mnafis.agameofbattleships.utilities

import android.media.MediaPlayer
import com.mnafis.agameofbattleships.utilities.AudioStatus.ON
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class TheMediaPlayer @Inject constructor(
    private val sharedPrefUtil: SharedPrefUtil,
    @Named("BackgroundMusic") private val backgroundMusic: MediaPlayer,
    @Named("AttackSound") private val menuSelectionEffect: MediaPlayer
) {

    private var soundStatus: AudioStatus = ON
    private var musicStatus: AudioStatus = ON

    fun updateSoundStatus(status: AudioStatus) {
            soundStatus = status
            sharedPrefUtil.setSoundStatus(status)
    }

    fun updateMusicStatus(status: AudioStatus) {
        musicStatus = status
        sharedPrefUtil.setMusicStatus(status)
    }

    fun playMenuSelectionEffect() {
        if (soundStatus == ON) {
            menuSelectionEffect.setVolume(0.10f, 0.10f)
            menuSelectionEffect.start()
        }
    }

    fun pauseMusic() {
        if (musicStatus == ON) backgroundMusic.pause()
    }

    fun stopMusic() {
        if (backgroundMusic.isPlaying) {
            backgroundMusic.stop()
            backgroundMusic.prepare()
        }
    }

    fun playMusic() {
        if (musicStatus == ON) backgroundMusic.start()
    }
}