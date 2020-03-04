package com.mnafis.agameofbattleships.utilities

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class EventBus @Inject constructor(){
    private val activityStartEventSubject = PublishSubject.create<FragmentSwitchEvent>()

    fun send(fragmentSwitchEvent: FragmentSwitchEvent) {
        activityStartEventSubject.onNext(fragmentSwitchEvent)
    }

    fun fragmentNavigationSubject(expectedViewModelClass: KClass<*>): Observable<FragmentSwitchEvent> {
        return activityStartEventSubject.filter { fromEmitter(it, expectedViewModelClass) }
    }

    private fun fromEmitter(event: BaseEvent, expectedViewModelClass: KClass<*>): Boolean {
        return event.emitter.javaClass.name == expectedViewModelClass.java.name
    }
}