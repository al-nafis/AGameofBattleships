package com.mnafis.agameofbattleships

import androidx.lifecycle.ViewModel
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil
import com.mnafis.agameofbattleships.utilities.TheMediaPlayer
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val sharedPrefUtil: SharedPrefUtil,
    private val theMediaPlayer: TheMediaPlayer
) : ViewModel() {

    fun onCreate() {
        theMediaPlayer.updateMusicStatus(sharedPrefUtil.getMusicStatus())
        theMediaPlayer.updateSoundStatus(sharedPrefUtil.getSoundStatus())
    }

    fun onResume() {
        theMediaPlayer.playMusic()
    }

    fun onPause() {
        theMediaPlayer.pauseMusic()
    }
}