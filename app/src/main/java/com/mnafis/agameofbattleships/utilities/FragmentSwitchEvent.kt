package com.mnafis.agameofbattleships.utilities

import androidx.lifecycle.ViewModel

class FragmentSwitchEvent(emitter: ViewModel, val scenario: Scenario) : BaseEvent(emitter) {
    enum class Scenario {
        GAME_DIFFICULTY_SET
    }
}