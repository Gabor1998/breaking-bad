package hu.bme.aut.breakingbad

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import hu.bme.aut.breakingbad.datasource.database.BreakingBadDatabase
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

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class DatabaseUnitTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    private val walter = CharacterEntity(
        id = 1,
        name = "Walter White",
        birthday = "09-07-1958",
        occupation = listOf("High School Chemistry Teacher", "Meth King Pin"),
        img = "https://images.amcnetworks.com/amc.com/wp-content/uploads/2015/04/cast_bb_700x1000_walter-white-lg.jpg",
        status = "Presumed dead",
        nickname = "Heisenberg",
        appearance = listOf(1, 2, 3, 4, 5),
        portrayed = "Bryan Cranston",
        category = "Breaking Bad"
    )
    private val jesse = CharacterEntity(
        id = 2,
        name = "Jesse Pinkman",
        birthday = "09-24-1984",
        occupation = listOf("Meth Dealer"),
        img = "https://vignette.wikia.nocookie.net/breakingbad/images/9/95/JesseS5.jpg/revision/latest?cb=20120620012441",
        status = "Alive",
        nickname = "Cap n' Cook",
        appearance = listOf(1, 2, 3, 4, 5),
        portrayed = "Aaron Paul",
        category = "Breaking Bad"
    )
    private val skyler = CharacterEntity(
        id = 3,
        name = "Skyler White",
        birthday = "08-11-1970",
        occupation = listOf("House wife", "Book Keeper", "Car Wash Manager", "Taxi Dispatcher"),
        img = "https://s-i.huffpost.com/gen/1317262/images/o-ANNA-GUNN-facebook.jpg",
        status = "Alive",
        nickname = "Sky",
        appearance = listOf(1, 2, 3, 4, 5),
        portrayed = "Anna Gunn",
        category = "Breaking Bad"
    )
    private val junior = CharacterEntity(
        id = 4,
        name = "Walter White Jr.",
        birthday = "07-08-1993",
        occupation = listOf("Teenager"),
        img = "https://media1.popsugar-assets.com/files/thumbor/WeLUSvbAMS_GL4iELYAUzu7Bpv0/fit-in/1024x1024/filters:format_auto-!!-:strip_icc-!!-/2018/01/12/910/n/1922283/fb758e62b5daf3c9_TCDBRBA_EC011/i/RJ-Mitte-Walter-White-Jr.jpg",
        status = "Alive",
        nickname = "Flynn",
        appearance = listOf(1, 2, 3, 4, 5),
        portrayed = "RJ Mitte",
        category = "Breaking Bad"
    )

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
        dao.insertCharacters(listOf(walter))

        // Assert
        assertEquals(1, dao.getCharactersByName().size)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun insertCharacterWithOnConflictStrategyTest() = runTest {
        // Arrange
        val dao = database.characterDao()

        // Act
        dao.insertCharacters(listOf(walter))
        dao.insertCharacters(listOf(walter.copy(name = "Walter White2")))

        // Assert
        assertEquals(1, dao.getCharactersByName().size)
        assertEquals("Walter White2", dao.getCharacter(walter.id)?.name)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getAllCharacterTest() = runTest {
        // Arrange
        val dao = database.characterDao()

        // Act
        dao.insertCharacters(listOf(walter, jesse, skyler, junior))

        // Assert
        assertEquals(4, dao.getCharactersByName().size)
        assertEquals(4, dao.getCharactersByName(null).size)
        assertEquals(4, dao.getCharactersByName("").size)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getCharactersByNameTest() = runTest {
        // Arrange
        val dao = database.characterDao()

        // Act
        dao.insertCharacters(listOf(walter, jesse, skyler, junior))

        // Assert
        assertEquals(2, dao.getCharactersByName("Walter").size)
        assertEquals(4, dao.getCharactersByName("e").size)
        assertEquals(0, dao.getCharactersByName("Gustavo Fring").size)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getCharacterTest() = runTest {
        // Arrange
        val dao = database.characterDao()

        // Act
        dao.insertCharacters(listOf(walter, jesse, skyler, junior))

        // Assert
        assertEquals(walter, dao.getCharacter(walter.id))
        assertEquals(jesse, dao.getCharacter(jesse.id))
        assertEquals(skyler, dao.getCharacter(skyler.id))
        assertEquals(junior, dao.getCharacter(junior.id))
    }

    @After
    fun close() {
        database.close()
    }
}