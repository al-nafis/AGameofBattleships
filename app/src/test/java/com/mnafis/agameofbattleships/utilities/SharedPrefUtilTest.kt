package com.mnafis.agameofbattleships.utilities

import android.content.SharedPreferences
import com.mnafis.agameofbattleships.utilities.SharedPrefUtil.Companion.DEFAULT_VALUE
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SharedPrefUtilTest {

    private val KEY = "key"
    private val STRING_VALUE = "value"
    private val BOOLEAN_VALUE = true
    private val INT_VALUE = 1

    private val sharedPreferences: SharedPreferences = mockk(relaxed = true)

    private lateinit var subject: SharedPrefUtil

    @Before
    fun setUp() {
        subject = SharedPrefUtil(sharedPreferences)
    }

    @Test
    fun setString_addsStringValueToSharedPref() {
        subject.setString(KEY, STRING_VALUE)

        verify { sharedPreferences.edit().putString(KEY, STRING_VALUE).apply() }
    }

    @Test
    fun getString_returnsValue() {
        every { sharedPreferences.getString(KEY, DEFAULT_VALUE) } returns STRING_VALUE

        assertEquals(STRING_VALUE, subject.getString(KEY))
    }

    @Test
    fun setInt_addsIntegerValueToSharedPref() {
        subject.setInt(KEY, INT_VALUE)

        verify { sharedPreferences.edit().putInt(KEY, INT_VALUE).apply() }
    }

    @Test
    fun getInt_returnsValue() {
        every { sharedPreferences.getInt(KEY, -1) } returns INT_VALUE

        assertEquals(INT_VALUE, subject.getInt(KEY))
    }
}