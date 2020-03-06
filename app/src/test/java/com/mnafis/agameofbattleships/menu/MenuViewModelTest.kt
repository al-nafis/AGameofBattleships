package com.mnafis.agameofbattleships.menu

import android.view.View
import com.mnafis.agameofbattleships.R
import com.mnafis.agameofbattleships.utilities.AudioStatus.OFF
import com.mnafis.agameofbattleships.utilities.AudioStatus.ON
import com.mnafis.agameofbattleships.utilities.EventBus
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil
import com.mnafis.agameofbattleships.utilities.TheMediaPlayer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test

class MenuViewModelTest {

    private val AUDIO_ON_TEXT = "On"
    private val AUDIO_OFF_TEXT = "Off"

    private val view: View = mockk(relaxed = true)
    private val eventBus: EventBus = mockk(relaxed = true)
    private val sharedPrefUtil: SharedPrefUtil = mockk(relaxed = true)
    private val theMediaPlayer: TheMediaPlayer = mockk(relaxed = true)

    private lateinit var subject: MenuViewModel

    @Before
    fun setUp() {
        subject = MenuViewModel(eventBus, sharedPrefUtil, theMediaPlayer)
    }

    @Test
    fun onResume_sharedPrefMusicStatusOn_updatesObservableFieldToOn() {
        every { sharedPrefUtil.getMusicStatus() } returns ON
        subject.musicText.set(AUDIO_OFF_TEXT)

        subject.onResume()

        assertEquals(AUDIO_ON_TEXT, subject.musicText.get())
    }

    @Test
    fun onResume_sharedPrefMusicStatusOff_updatesObservableFieldToOff() {
        every { sharedPrefUtil.getMusicStatus() } returns OFF
        subject.musicText.set(AUDIO_ON_TEXT)

        subject.onResume()

        assertEquals(AUDIO_OFF_TEXT, subject.musicText.get())
    }

    @Test
    fun onResume_sharedPrefSoundStatusOn_updatesObservableFieldToOn() {
        every { sharedPrefUtil.getSoundStatus() } returns ON
        subject.soundText.set(AUDIO_OFF_TEXT)

        subject.onResume()

        assertEquals(AUDIO_ON_TEXT, subject.soundText.get())
    }

    @Test
    fun onResume_sharedPrefSoundStatusOff_updatesObservableFieldToOff() {
        every { sharedPrefUtil.getSoundStatus() } returns OFF
        subject.soundText.set(AUDIO_ON_TEXT)

        subject.onResume()

        assertEquals(AUDIO_OFF_TEXT, subject.soundText.get())
    }

    @Test
    fun onClickMenuButton_playsMenuSelectionSound() {
        subject.onClickMenuButton(view)

        verify { theMediaPlayer.playMenuSelectionEffect() }
    }

    @Test
    fun onClickMenuButton_playButtonClicked_displayMainMenu() {
        every { view.id } returns R.id.play_button

        subject.onClickMenuButton(view)

        assertTrue(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertFalse(subject.menuGameDifficultyActive.get()!!)
        assertFalse(subject.menuOptionsActive.get()!!)
        assertFalse(subject.menuCreditsActive.get()!!)
    }

    @Test
    fun onClickMenuButton_gameDifficultyBackButtonClicked_displayMainMenu() {
        every { view.id } returns R.id.game_difficulty_back_button

        subject.onClickMenuButton(view)

        assertTrue(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertFalse(subject.menuGameDifficultyActive.get()!!)
        assertFalse(subject.menuOptionsActive.get()!!)
        assertFalse(subject.menuCreditsActive.get()!!)
    }

    @Test
    fun onClickMenuButton_menuOptionsBackButtonClicked_displayMainMenu() {
        every { view.id } returns R.id.menu_options_back_button

        subject.onClickMenuButton(view)

        assertTrue(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertFalse(subject.menuGameDifficultyActive.get()!!)
        assertFalse(subject.menuOptionsActive.get()!!)
        assertFalse(subject.menuCreditsActive.get()!!)
    }

    @Test
    fun onClickMenuButton_menuCreditsBackButtonClicked_displayMainMenu() {
        every { view.id } returns R.id.menu_credits_back_button

        subject.onClickMenuButton(view)

        assertTrue(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertFalse(subject.menuGameDifficultyActive.get()!!)
        assertFalse(subject.menuOptionsActive.get()!!)
        assertFalse(subject.menuCreditsActive.get()!!)
    }

    @Test
    fun onClickMenuButton_newGameButtonClicked_displayGameDifficultyMenu() {
        every { view.id } returns R.id.new_game_button

        subject.onClickMenuButton(view)

        assertFalse(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertTrue(subject.menuGameDifficultyActive.get()!!)
        assertFalse(subject.menuOptionsActive.get()!!)
        assertFalse(subject.menuCreditsActive.get()!!)
    }

    @Test
    fun onClickMenuButton_gameDifficultyEasyButtonClicked_savesDifficultyValueInSharedPrefAndLaunchesShipPlacementScreen() {
        every { view.id } returns R.id.game_difficulty_easy_button

        subject.onClickMenuButton(view)

        assertFalse(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertFalse(subject.menuGameDifficultyActive.get()!!)
        assertFalse(subject.menuOptionsActive.get()!!)
        assertFalse(subject.menuCreditsActive.get()!!)

        //todo: needs to be implemented
    }

    @Test
    fun onClickMenuButton_gameDifficultyNormalButtonClicked_savesDifficultyValueInSharedPrefAndLaunchesShipPlacementScreen() {
        every { view.id } returns R.id.game_difficulty_Normal_button

        subject.onClickMenuButton(view)

        assertFalse(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertFalse(subject.menuGameDifficultyActive.get()!!)
        assertFalse(subject.menuOptionsActive.get()!!)
        assertFalse(subject.menuCreditsActive.get()!!)

        //todo: needs to be implemented
    }

    @Test
    fun onClickMenuButton_optionsButtonClicked_displaysOptionsMenu() {
        every { view.id } returns R.id.options_button

        subject.onClickMenuButton(view)

        assertFalse(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertFalse(subject.menuGameDifficultyActive.get()!!)
        assertTrue(subject.menuOptionsActive.get()!!)
        assertFalse(subject.menuCreditsActive.get()!!)
    }

    @Test
    fun onClickMenuButton_creditsButtonClicked_displaysCreditsMenu() {
        every { view.id } returns R.id.credits_button

        subject.onClickMenuButton(view)

        assertFalse(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertFalse(subject.menuGameDifficultyActive.get()!!)
        assertFalse(subject.menuOptionsActive.get()!!)
        assertTrue(subject.menuCreditsActive.get()!!)
    }

    @Test
    fun onClickMenuButton_musicSettingsButtonClickedAndMusicWasOff_updatesTextAndPlaysMusic() {
        every { view.id } returns R.id.music_settings_button
        subject.musicText.set(AUDIO_OFF_TEXT)

        subject.onClickMenuButton(view)

        verify {
            theMediaPlayer.playMusic()
            theMediaPlayer.updateMusicStatus(ON)
        }
        assertEquals(AUDIO_ON_TEXT, subject.musicText.get())
        assertFalse(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertFalse(subject.menuGameDifficultyActive.get()!!)
        assertTrue(subject.menuOptionsActive.get()!!)
        assertFalse(subject.menuCreditsActive.get()!!)
    }

    @Test
    fun onClickMenuButton_musicSettingsButtonClickedAndMusicWasOn_updatesTextAndStopsMusic() {
        every { view.id } returns R.id.music_settings_button
        subject.musicText.set(AUDIO_ON_TEXT)

        subject.onClickMenuButton(view)

        verify {
            theMediaPlayer.stopMusic()
            theMediaPlayer.updateMusicStatus(OFF)
        }
        assertEquals(AUDIO_OFF_TEXT, subject.musicText.get())
        assertFalse(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertFalse(subject.menuGameDifficultyActive.get()!!)
        assertTrue(subject.menuOptionsActive.get()!!)
        assertFalse(subject.menuCreditsActive.get()!!)
    }

    @Test
    fun onClickMenuButton_soundSettingsButtonClickedAndSoundWasOff_updatesTextAndTheMediaPlayer() {
        every { view.id } returns R.id.sound_settings_button
        subject.soundText.set(AUDIO_OFF_TEXT)

        subject.onClickMenuButton(view)

        verify { theMediaPlayer.updateSoundStatus(ON) }
        assertEquals(AUDIO_ON_TEXT, subject.soundText.get())
        assertFalse(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertFalse(subject.menuGameDifficultyActive.get()!!)
        assertTrue(subject.menuOptionsActive.get()!!)
        assertFalse(subject.menuCreditsActive.get()!!)
    }

    @Test
    fun onClickMenuButton_soundSettingsButtonClickedAndSoundWasOn_updatesTextAndTheMediaPlayer() {
        every { view.id } returns R.id.sound_settings_button
        subject.soundText.set(AUDIO_ON_TEXT)

        subject.onClickMenuButton(view)

        verify { theMediaPlayer.updateSoundStatus(OFF) }
        assertEquals(AUDIO_OFF_TEXT, subject.soundText.get())
        assertFalse(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertFalse(subject.menuGameDifficultyActive.get()!!)
        assertTrue(subject.menuOptionsActive.get()!!)
        assertFalse(subject.menuCreditsActive.get()!!)
    }
}