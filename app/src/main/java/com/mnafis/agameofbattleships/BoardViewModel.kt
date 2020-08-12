package com.mnafis.agameofbattleships

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class BoardViewModel @Inject constructor(): ViewModel() {
    val ROW_SIZE = 8
    val COLUMN_SIZE = 8
}