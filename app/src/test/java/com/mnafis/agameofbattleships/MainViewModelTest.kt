package com.mnafis.agameofbattleships

import com.mnafis.agameofbattleships.utilities.SharedPrefUtil
import com.mnafis.agameofbattleships.utilities.TheMediaPlayer
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class MainViewModelTest {

    private val sharedPrefUtil: SharedPrefUtil = mockk(relaxed = true)
    private val theMediaPlayer: TheMediaPlayer= mockk(relaxed = true)

    lateinit var subject: MainViewModel

    @Before
    fun setUp() {
        subject = MainViewModel(sharedPrefUtil, theMediaPlayer)
    }

    @Test
    fun onResume_playsMusic() {
        subject.onResume()

        verify { theMediaPlayer.playMusic() }
    }

    @Test
    fun onPause_pausesMusic() {
        subject.onPause()

        verify { theMediaPlayer.pauseMusic() }
    }
}