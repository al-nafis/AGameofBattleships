package com.mnafis.agameofbattleships.menu

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.mnafis.agameofbattleships.R
import com.mnafis.agameofbattleships.utilities.EventBus
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.MUSIC_STATUS
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.SOUND_STATUS
import com.mnafis.agameofbattleships.utilities.TheMediaPlayer
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val eventBus: EventBus,
    private val sharedPrefUtil: SharedPrefUtil,
    private val theMediaPlayer: TheMediaPlayer
) : ViewModel() {

    val backButtonText = "<-"

    val musicText = ObservableField<String>("On")
    val soundText = ObservableField<String>("On")
    val menuWelcomeActive = ObservableField<Boolean>(true)
    val menuMainActive = ObservableField<Boolean>(false)
    val menuGameDifficultyActive = ObservableField<Boolean>(false)
    val menuOptionsActive = ObservableField<Boolean>(false)
    val menuCreditsActive = ObservableField<Boolean>(false)

    fun onResume() {
        Log.d("AWESOME", "JOB")
        sharedPrefUtil.getBoolean(MUSIC_STATUS)?.let { musicText.set(if (it) "On" else "Off") }
        sharedPrefUtil.getBoolean(SOUND_STATUS)?.let { soundText.set(if (it) "On" else "Off") }
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
        menuGameDifficultyActive.set(true) //for Testing
    }

    private fun updateMusicStatus() {
        if (musicText.get() == "On") {
            musicText.set("Off")
            theMediaPlayer.musicStatus = false
            theMediaPlayer.stopMusic()
            sharedPrefUtil.setBoolean(MUSIC_STATUS, false)
        } else {
            musicText.set("On")
            theMediaPlayer.musicStatus = true
            theMediaPlayer.playMusic()
            sharedPrefUtil.setBoolean(MUSIC_STATUS, true)
        }
    }

    private fun updateSoundStatus() {
        if (soundText.get() == "On") {
            soundText.set("Off")
            theMediaPlayer.soundStatus = false
            sharedPrefUtil.setBoolean(SOUND_STATUS, false)
        } else {
            soundText.set("On")
            theMediaPlayer.soundStatus = true
            sharedPrefUtil.setBoolean(SOUND_STATUS, true)
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
