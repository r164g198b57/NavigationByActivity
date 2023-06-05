package com.github.r164g198b57.navigationbyactivity.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Options (
    val boxCount: Int,
    val isTimerEnabled: Boolean
        ) : Parcelable{
            companion object{
                @JvmStatic val DEFAULT = Options( 3, false)
            }
}