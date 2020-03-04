package com.mnafis.agameofbattleships.utilities

import android.content.Context
import android.media.MediaPlayer
import com.mnafis.agameofbattleships.R
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class TheMediaPlayer @Inject constructor(@Named("ApplicationContext") private val context: Context, private val sharedPrefUtil: SharedPrefUtil) {
    private val MAIN_MENU_MUSIC: Int = R.raw.main_menu
    private val mediaPlayer: MediaPlayer

    var soundStatus = true
    var musicStatus = true

    fun playHitMusic() {
        if (soundStatus) {
            val soundPlayer = MediaPlayer.create(context, R.raw.shotgun)
            soundPlayer.setVolume(0.15f, 0.15f)
            soundPlayer.start()
        }
    }

    fun pauseMusic() {
        if(musicStatus) mediaPlayer.pause()
    }

    fun stopMusic() {
        mediaPlayer.stop()
        mediaPlayer.prepare()
    }

    fun playMusic() {
        if (musicStatus) mediaPlayer.start()
    }

    init {
        mediaPlayer = MediaPlayer.create(context, MAIN_MENU_MUSIC)
    }
}