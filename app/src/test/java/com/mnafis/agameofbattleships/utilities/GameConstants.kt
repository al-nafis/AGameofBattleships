package com.mnafis.agameofbattleships.utilities

import androidx.annotation.StringDef

@Retention(AnnotationRetention.SOURCE)
@StringDef(EASY, NORMAL)
annotation class Difficulty
const val EASY = "easy"
const val NORMAL = "normal"