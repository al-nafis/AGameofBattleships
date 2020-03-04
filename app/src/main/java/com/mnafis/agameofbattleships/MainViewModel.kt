package com.mnafis.agameofbattleships

import androidx.lifecycle.ViewModel
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil
import com.mnafis.agameofbattleships.utilities.TheMediaPlayer
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val sharedPrefUtil: SharedPrefUtil,
    private val theMediaPlayer: TheMediaPlayer
) : ViewModel() {

    fun onResume() {
        sharedPrefUtil.getBoolean(SharedPrefUtil.MUSIC_STATUS)?.let { theMediaPlayer.musicStatus = it }
        sharedPrefUtil.getBoolean(SharedPrefUtil.SOUND_STATUS)?.let { theMediaPlayer.soundStatus = it }
        theMediaPlayer.playMusic()
    }

    fun onPause() {
        theMediaPlayer.pauseMusic()
    }
}