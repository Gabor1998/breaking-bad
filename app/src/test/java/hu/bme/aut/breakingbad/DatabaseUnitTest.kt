package hu.bme.aut.breakingbad

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import hu.bme.aut.breakingbad.datasource.database.BreakingBadDatabase
import hu.bme.aut.breakingbad.di.PersistenceModule
import hu.bme.aut.breakingbad.model.CharacterEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

@UninstallModules(PersistenceModule::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class DatabaseUnitTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltAndroidRule.inject()
    }

    @Inject
    lateinit var database: BreakingBadDatabase

    @ExperimentalCoroutinesApi
    @Test
    fun insertCharacterTest() = runTest {
        // Arrange
        val dao = database.characterDao()

        // Act
        dao.insertCharacters(listOf(CharacterEntity(id = 1, name = "Walter White")))

        // Assert
        assertEquals(1, dao.getCharactersByName(null).size)
    }

    @After
    fun close() {
        database.close()
    }
}