package com.mnafis.agameofbattleships

import android.content.Context

class Cell(context: Context, val rowNumber: Int, val columnNumber: Int) :
    androidx.appcompat.widget.AppCompatImageButton(context) {
    init {
//        setBackgroundColor(resources.getColor(R.color.colorPrimaryDark))
    }
}