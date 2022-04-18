package hu.bme.aut.breakingbad

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import hu.bme.aut.breakingbad.datasource.network.BreakingBadApi
import hu.bme.aut.breakingbad.model.CharacterDto
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

    private fun readJsonFromAssets(context: Context, filePath: String): String? {
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
    fun insertCharacterTest() = runTest {
        // Arrange
        mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(readJsonFromAssets(context, "getCharacter1.json").orEmpty()))

        // Act
        val actualDto = api.getCharacter(1)

        // Assert
        val expectedDto = CharacterDto(
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
        assertEquals(expectedDto, actualDto)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}