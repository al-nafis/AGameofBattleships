package com.mnafis.agameofbattleships.menu

import android.view.View
import com.mnafis.agameofbattleships.R
import com.mnafis.agameofbattleships.utilities.EventBus
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.DEFAULT_VALUE
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.MUSIC_STATUS
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.SOUND_STATUS
import com.mnafis.agameofbattleships.utilities.TheMediaPlayer
import com.mnafis.agameofbattleships.utilities.TheMediaPlayer.Companion.OFF
import com.mnafis.agameofbattleships.utilities.TheMediaPlayer.Companion.ON
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.Assert.*
import org.junit.Before
import org.junit.Test

class MenuViewModelTest {

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
    fun onResume_musicStatusDefaultValue_doesNothing() {
        every { sharedPrefUtil.getString(MUSIC_STATUS) } returns DEFAULT_VALUE
        subject.musicText.set(ON)

        subject.onResume()

        assertEquals(ON, subject.musicText.get())
    }

    @Test
    fun onResume_musicStatusNotDefaultValue_setsMusicDisplayString() {
        every { sharedPrefUtil.getString(MUSIC_STATUS) } returns OFF
        subject.musicText.set(ON)

        subject.onResume()

        assertEquals(OFF, subject.musicText.get())
    }

    @Test
    fun onResume_soundStatusDefaultValue_doesNothing() {
        every { sharedPrefUtil.getString(SOUND_STATUS) } returns DEFAULT_VALUE
        subject.soundText.set(ON)

        subject.onResume()

        assertEquals(ON, subject.soundText.get())
    }

    @Test
    fun onResume_soundStatusNotDefaultValue_setsSoundDisplayString() {
        every { sharedPrefUtil.getString(SOUND_STATUS) } returns OFF
        subject.soundText.set(ON)

        subject.onResume()

        assertEquals(OFF, subject.soundText.get())
    }

    @Test
    fun onClickMenuButton_playsHitSound() {
        subject.onClickMenuButton(view)

        verify { theMediaPlayer.playHitMusic() }
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
        subject.musicText.set(OFF)

        subject.onClickMenuButton(view)

        verify { theMediaPlayer.playMusic() }
        assertEquals(ON, subject.musicText.get())
        assertFalse(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertFalse(subject.menuGameDifficultyActive.get()!!)
        assertTrue(subject.menuOptionsActive.get()!!)
        assertFalse(subject.menuCreditsActive.get()!!)
    }

    @Test
    fun onClickMenuButton_musicSettingsButtonClickedAndMusicWasOn_updatesTextAndStopsMusic() {
        every { view.id } returns R.id.music_settings_button
        subject.musicText.set(ON)

        subject.onClickMenuButton(view)

        verify { theMediaPlayer.stopMusic() }
        assertEquals(OFF, subject.musicText.get())
        assertFalse(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertFalse(subject.menuGameDifficultyActive.get()!!)
        assertTrue(subject.menuOptionsActive.get()!!)
        assertFalse(subject.menuCreditsActive.get()!!)
    }

    @Test
    fun onClickMenuButton_soundSettingsButtonClickedAndSoundWasOff_updatesTextAndTheMediaPlayer() {
        every { view.id } returns R.id.sound_settings_button
        subject.soundText.set(OFF)

        subject.onClickMenuButton(view)

        verify { theMediaPlayer.updateSoundStatus(ON) }
        assertEquals(ON, subject.soundText.get())
        assertFalse(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertFalse(subject.menuGameDifficultyActive.get()!!)
        assertTrue(subject.menuOptionsActive.get()!!)
        assertFalse(subject.menuCreditsActive.get()!!)
    }

    @Test
    fun onClickMenuButton_soundSettingsButtonClickedAndSoundWasOn_updatesTextAndTheMediaPlayer() {
        every { view.id } returns R.id.sound_settings_button
        subject.soundText.set(ON)

        subject.onClickMenuButton(view)

        verify { theMediaPlayer.updateSoundStatus(OFF) }
        assertEquals(OFF, subject.soundText.get())
        assertFalse(subject.menuMainActive.get()!!)
        assertFalse(subject.menuWelcomeActive.get()!!)
        assertFalse(subject.menuGameDifficultyActive.get()!!)
        assertTrue(subject.menuOptionsActive.get()!!)
        assertFalse(subject.menuCreditsActive.get()!!)
    }
}