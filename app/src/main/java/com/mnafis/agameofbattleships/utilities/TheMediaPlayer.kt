package com.mnafis.agameofbattleships.utilities

import android.media.MediaPlayer
import androidx.annotation.StringDef
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.DEFAULT_VALUE
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.MUSIC_STATUS
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.SOUND_STATUS
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class TheMediaPlayer @Inject constructor(
    private val sharedPrefUtil: SharedPrefUtil,
    @Named("BackgroundMusic") private val mediaPlayer: MediaPlayer,
    @Named("AttackSound") private val menuSelectionEffect: MediaPlayer
) {

    private var soundStatus = ON
    private var musicStatus = ON

    fun updateSoundStatus(status: String) {
        if (status != DEFAULT_VALUE) {
            soundStatus = status
            sharedPrefUtil.setString(SOUND_STATUS, status)
        }
    }

    fun updateMusicStatus(@StatusKey status: String) {
        if (status != DEFAULT_VALUE) {
            musicStatus = status
            sharedPrefUtil.setString(MUSIC_STATUS, status)
        }
    }

    fun playMenuSelectionEffect() {
        if (soundStatus == ON) {
            menuSelectionEffect.setVolume(0.15f, 0.15f)
            menuSelectionEffect.start()
        }
    }

    fun pauseMusic() {
        if (musicStatus == ON) mediaPlayer.pause()
    }

    fun stopMusic() {
        mediaPlayer.stop()
        mediaPlayer.prepare()
    }

    fun playMusic() {
        if (musicStatus == ON) mediaPlayer.start()
    }

    @Retention(AnnotationRetention.SOURCE)
    @StringDef(ON, OFF)
    annotation class StatusKey

    companion object{
        const val ON = "On"
        const val OFF = "Off"
    }
}