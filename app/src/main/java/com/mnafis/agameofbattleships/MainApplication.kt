package com.mnafis.agameofbattleships

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.mnafis.agameofbattleships.dagger_components.ContextModule
import com.mnafis.agameofbattleships.dagger_components.DaggerMainApplicationComponent
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil
import com.mnafis.agameofbattleships.utilities.TheMediaPlayer
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var theMediaPlayer: TheMediaPlayer

    @Inject
    lateinit var sharedPrefUtil: SharedPrefUtil

    override fun onCreate() {
        super.onCreate()
        DaggerMainApplicationComponent.builder().contextModule(ContextModule(this)).build()
            .inject(this)

        sharedPrefUtil.getBoolean(SharedPrefUtil.MUSIC_STATUS)?.let {
            theMediaPlayer.musicStatus = it
        } ?.run {
            theMediaPlayer.musicStatus = true
        }

        sharedPrefUtil.getBoolean(SharedPrefUtil.SOUND_STATUS)?.let {
            theMediaPlayer.soundStatus = it
        } ?.run {
            theMediaPlayer.soundStatus = true
        }
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = activityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector
}