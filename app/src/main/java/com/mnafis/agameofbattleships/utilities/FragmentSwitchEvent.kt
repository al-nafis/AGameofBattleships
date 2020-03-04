package com.mnafis.agameofbattleships.utilities

import androidx.lifecycle.ViewModel
import kotlin.reflect.KClass

class FragmentSwitchEvent(emitter: ViewModel, val destinationFragment: KClass<*>) : BaseEvent(emitter)