package com.mnafis.agameofbattleships.menu

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.mnafis.agameofbattleships.R
import com.mnafis.agameofbattleships.utilities.AudioStatus.OFF
import com.mnafis.agameofbattleships.utilities.AudioStatus.ON
import com.mnafis.agameofbattleships.utilities.EventBus
import com.mnafis.agameofbattleships.utilities.GameDifficulty
import com.mnafis.agameofbattleships.utilities.GameDifficulty.EASY
import com.mnafis.agameofbattleships.utilities.GameDifficulty.NORMAL
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil
import com.mnafis.agameofbattleships.utilities.TheMediaPlayer
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val eventBus: EventBus,
    private val sharedPrefUtil: SharedPrefUtil,
    private val theMediaPlayer: TheMediaPlayer
) : ViewModel() {

    private val AUDIO_ON_TEXT = "On"
    private val AUDIO_OFF_TEXT = "Off"

    val backButtonText = "<-"

    val musicText = ObservableField<String>(AUDIO_ON_TEXT)
    val soundText = ObservableField<String>(AUDIO_ON_TEXT)
    val menuWelcomeActive = ObservableField<Boolean>(true)
    val menuMainActive = ObservableField<Boolean>(false)
    val menuGameDifficultyActive = ObservableField<Boolean>(false)
    val menuOptionsActive = ObservableField<Boolean>(false)
    val menuCreditsActive = ObservableField<Boolean>(false)

    fun onResume() {
        if (sharedPrefUtil.getMusicStatus().value)
            musicText.set(AUDIO_ON_TEXT) else musicText.set(AUDIO_OFF_TEXT)
        if (sharedPrefUtil.getSoundStatus().value)
            soundText.set(AUDIO_ON_TEXT) else soundText.set(AUDIO_OFF_TEXT)
    }

    fun onClickMenuButton(view: View) {
        resetMenusVisibility()
        when (view.id) {
            R.id.play_button, R.id.game_difficulty_back_button,
            R.id.menu_options_back_button, R.id.menu_credits_back_button -> menuMainActive.set(true)
            R.id.new_game_button -> menuGameDifficultyActive.set(true)
            R.id.game_difficulty_easy_button -> setGameDifficultyAndBegin(EASY)
            R.id.game_difficulty_Normal_button -> setGameDifficultyAndBegin(NORMAL)
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
        theMediaPlayer.playMenuSelectionEffect()
    }

    private fun setGameDifficultyAndBegin(gameDifficulty: GameDifficulty) {
        //todo: needs to be implemented
    }

    private fun updateMusicStatus() {
        if (musicText.get() == AUDIO_ON_TEXT) {
            musicText.set(AUDIO_OFF_TEXT)
            theMediaPlayer.updateMusicStatus(OFF)
            theMediaPlayer.stopMusic()
        } else {
            musicText.set(AUDIO_ON_TEXT)
            theMediaPlayer.updateMusicStatus(ON)
            theMediaPlayer.playMusic()
        }
    }

    private fun updateSoundStatus() {
        if (soundText.get() == AUDIO_ON_TEXT) {
            soundText.set(AUDIO_OFF_TEXT)
            theMediaPlayer.updateSoundStatus(OFF)
        } else {
            soundText.set(AUDIO_ON_TEXT)
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
