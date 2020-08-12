package com.mnafis.agameofbattleships.fleet_setup

import com.mnafis.agameofbattleships.utilities.EventBus
import io.mockk.mockk
import org.junit.Before

import org.junit.Assert.*

class FleetSetupViewModelTest {

    private val eventBus: EventBus = mockk()

    private lateinit var subject: FleetSetupViewModel

    @Before
    fun setUp() {
        subject = FleetSetupViewModel(eventBus)
    }
}