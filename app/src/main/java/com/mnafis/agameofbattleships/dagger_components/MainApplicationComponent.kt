package com.mnafis.agameofbattleships.dagger_components

import com.mnafis.agameofbattleships.MainApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, ViewModelModule::class, ContextModule::class, SharedModule::class])
interface MainApplicationComponent : AndroidInjector<MainApplication>