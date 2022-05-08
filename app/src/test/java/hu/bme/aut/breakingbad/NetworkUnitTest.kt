package hu.bme.aut.breakingbad

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import hu.bme.aut.breakingbad.datasource.network.BreakingBadApi
import hu.bme.aut.breakingbad.model.CharacterDto
import hu.bme.aut.breakingbad.model.QuoteDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class NetworkUnitTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltAndroidRule.inject()
    }

    @Inject
    lateinit var api: BreakingBadApi

    @Inject
    lateinit var mockWebServer: MockWebServer

    @ApplicationContext
    @Inject
    lateinit var context: Context

    private val walter = CharacterDto(
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
    private val jesse = CharacterDto(
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
    private val skyler = CharacterDto(
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
    private val junior = CharacterDto(
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

    private val quote = QuoteDto(
        id = 18,
        quote = "We make poison for people who don’t care. We probably have the most unpicky customers in the world.",
        author = "Jesse Pinkman"
    )

    private fun readJsonFromAssets(filePath: String): String? {
        return try {
            val source = context.assets.open(filePath).source().buffer()
            source.readByteString().string(Charset.forName("utf-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getCharacterTest() = runTest {
        // Arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(readJsonFromAssets("getCharacter1.json").orEmpty()))
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(readJsonFromAssets("getCharacter2.json").orEmpty()))

        // Act
        val character1 = api.getCharacter(1)
        val character2 = api.getCharacter(2)

        // Assert
        assertEquals(walter, character1)
        assertEquals(jesse, character2)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getCharacterListTest() = runTest {
        // Arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(readJsonFromAssets("getCharacters.json").orEmpty()))

        // Act
        val characters = api.getCharactersByName()

        // Assert
        assertEquals(listOf(walter, jesse, skyler, junior), characters)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getCharactersByNameTest() = runTest {
        // Arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(readJsonFromAssets("getCharactersByName.json").orEmpty()))

        // Act
        val characters = api.getCharactersByName("Walter")

        // Assert
        assertEquals(listOf(walter, junior), characters)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getRandomQuoteByAuthorTest() = runTest {
        // Arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(readJsonFromAssets("getRandomQuoteByAuthor.json").orEmpty()))

        // Act
        val randomQuote = api.getRandomQuoteByAuthor("Jesse Pinkman")

        // Assert
        assertEquals(listOf(quote), randomQuote)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}