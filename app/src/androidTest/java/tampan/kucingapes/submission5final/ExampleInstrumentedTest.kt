/*
 * ExampleInstrumentedTest.kt on Submission5final
 * Developed by Muhammad Utsman
 * Last modified 10/27/18 4:05 PM
 * Copyright (c) 2018 kucingapes
 */

package tampan.kucingapes.submission5final

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("tampan.kucingapes.submission5final", appContext.packageName)
    }
}
