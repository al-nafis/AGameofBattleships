package com.mnafis.agameofbattleships.dagger_components

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import com.mnafis.agameofbattleships.R
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class SharedModule {
    @Provides
    fun sharedPreferences(@Named("ApplicationContext") context: Context): SharedPreferences =
        context.getSharedPreferences("appPref", Context.MODE_PRIVATE)

    @Provides
    @Named("BackgroundMusic")
    fun backgroundPlayer(@Named("ApplicationContext") context: Context): MediaPlayer =
        MediaPlayer.create(context, R.raw.main_menu)

    @Provides
    @Named("AttackSound")
    fun attackSoundPlayer(@Named("ApplicationContext") context: Context): MediaPlayer =
        MediaPlayer.create(context, R.raw.shotgun)
}