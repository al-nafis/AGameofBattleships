package com.mnafis.agameofbattleships.utilities

import com.mnafis.agameofbattleships.MainViewModel
import com.mnafis.agameofbattleships.menu.MenuViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class EventBusTest {

    private val viewModel: MainViewModel = mockk()
    private val event: FragmentSwitchEvent = mockk()

    private lateinit var subject: EventBus

    @Before
    fun setUp() {
        every { event.emitter } returns viewModel

        subject = EventBus()
    }

    @Test
    fun fragmentNavigationSubject_classNameMatches_returnsPublishSubject() {
        var testNumber = 0

        subject.fragmentNavigationSubject(MainViewModel::class).subscribe{ testNumber = 1 }
        subject.send(event)

        assertEquals(1, testNumber)
    }

    @Test
    fun fragmentNavigationSubject_classNameDoesNotMatch_doesNotReturnPublishSubject() {
        var testNumber = 0

        subject.fragmentNavigationSubject(MenuViewModel::class).subscribe{ testNumber = 1 }
        subject.send(event)

        assertEquals(0, testNumber)
    }
}