package com.mnafis.agameofbattleships.menu

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.mnafis.agameofbattleships.R
import com.mnafis.agameofbattleships.utilities.EventBus
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.DEFAULT_VALUE
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.MUSIC_STATUS
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.SOUND_STATUS
import com.mnafis.agameofbattleships.utilities.TheMediaPlayer
import com.mnafis.agameofbattleships.utilities.TheMediaPlayer.Companion.OFF
import com.mnafis.agameofbattleships.utilities.TheMediaPlayer.Companion.ON
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val eventBus: EventBus,
    private val sharedPrefUtil: SharedPrefUtil,
    private val theMediaPlayer: TheMediaPlayer
) : ViewModel() {

    val backButtonText = "<-"

    val musicText = ObservableField<String>(ON)
    val soundText = ObservableField<String>(ON)
    val menuWelcomeActive = ObservableField<Boolean>(true)
    val menuMainActive = ObservableField<Boolean>(false)
    val menuGameDifficultyActive = ObservableField<Boolean>(false)
    val menuOptionsActive = ObservableField<Boolean>(false)
    val menuCreditsActive = ObservableField<Boolean>(false)

    fun onResume() {
        if (sharedPrefUtil.getString(MUSIC_STATUS) != DEFAULT_VALUE) musicText.set(sharedPrefUtil.getString(MUSIC_STATUS))
        if (sharedPrefUtil.getString(SOUND_STATUS) != DEFAULT_VALUE) soundText.set(sharedPrefUtil.getString(SOUND_STATUS))
    }

    fun onClickMenuButton(view: View) {
        resetMenusVisibility()
        when (view.id) {
            R.id.play_button, R.id.game_difficulty_back_button,
            R.id.menu_options_back_button, R.id.menu_credits_back_button -> menuMainActive.set(true)
            R.id.new_game_button -> menuGameDifficultyActive.set(true)
            R.id.game_difficulty_easy_button -> setGameDifficultyAndBegin("easy")
            R.id.game_difficulty_Normal_button -> setGameDifficultyAndBegin("normal")
            R.id.options_button -> menuOptionsActive.set(true)
            R.id.credits_button -> menuCreditsActive.set(true)
            R.id.music_settings_button -> {
                menuOptionsActive.set(true)
                updateMusicStatus()
            }
            R.id.sound_settings_button -> {
                menuOptionsActive.set(true)
                updateSoundStatus()
            }
        }
        makeSelectionSound()
    }

    private fun makeSelectionSound() {
        theMediaPlayer.playHitMusic()
    }

    private fun setGameDifficultyAndBegin(gameDifficulty: String) {
        //todo: needs to be implemented
    }

    private fun updateMusicStatus() {
        if (musicText.get() == ON) {
            musicText.set(OFF)
            theMediaPlayer.updateMusicStatus(OFF)
            theMediaPlayer.stopMusic()
        } else {
            musicText.set(ON)
            theMediaPlayer.updateMusicStatus(ON)
            theMediaPlayer.playMusic()
        }
    }

    private fun updateSoundStatus() {
        if (soundText.get() == ON) {
            soundText.set(OFF)
            theMediaPlayer.updateSoundStatus(OFF)
        } else {
            soundText.set(ON)
            theMediaPlayer.updateSoundStatus(ON)
        }
    }

    private fun resetMenusVisibility() {
        menuWelcomeActive.set(false)
        menuMainActive.set(false)
        menuGameDifficultyActive.set(false)
        menuOptionsActive.set(false)
        menuCreditsActive.set(false)
    }
}
