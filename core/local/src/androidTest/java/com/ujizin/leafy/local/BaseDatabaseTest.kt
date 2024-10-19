package com.ujizin.leafy.local

import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.ujizin.leafy.core.local.Database
import com.ujizin.leafy.core.test.TestDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
abstract class BaseDatabaseTest {

    @get:Rule val mainDispatcherRule = TestDispatcherRule()

    protected lateinit var db: Database

    @Before
    open fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(context, Database::class.java).build()
    }

    @After
    open fun tearsDown() {
        db.close()
    }
}
