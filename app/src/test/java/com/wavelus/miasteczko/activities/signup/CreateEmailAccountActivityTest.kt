package com.wavelus.miasteczko.activities.signup

import android.support.v7.app.AppCompatActivity
import com.wavelus.miasteczko.R
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)

class CreateEmailAccountActivityTest {
    private lateinit var signupEmailAccountActivity: CreateEmailAccountActivity
    private var mAppCompatActivity: AppCompatActivity? = null

    @Before
    fun setUp() {
        signupEmailAccountActivity = Robolectric.setupActivity(CreateEmailAccountActivity::class.java)
        mAppCompatActivity = Robolectric.setupActivity(CreateEmailAccountActivity::class.java)
    }

    @Test
    fun getMAuth() {
    }

    @Test
    fun setMAuth() {
    }

    @Test
    fun getMDatabase() {
    }

    @Test
    fun setMDatabase() {
    }

    @Test
    fun onCreate() {
        assertEquals(
            R.id.createEmailAccountLayout.toLong(),
            shadowOf(mAppCompatActivity).getContentView().getId().toLong()
        )

    }

    @Test
    fun createAccount() {
    }
}