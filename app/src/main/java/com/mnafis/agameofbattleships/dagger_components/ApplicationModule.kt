package com.mnafis.agameofbattleships.dagger_components

import com.mnafis.agameofbattleships.menu.MenuFragment
import com.mnafis.agameofbattleships.MainActivity
import com.mnafis.agameofbattleships.pause.PauseFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ApplicationModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun menuFragment(): MenuFragment

    @ContributesAndroidInjector
    abstract fun pauseFragment(): PauseFragment
}