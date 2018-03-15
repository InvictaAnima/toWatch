package com.ferumate.towatch.commons.data.remote

import com.ferumate.towatch.commons.testing.DependencyProvider
import com.ferumate.towatch.commons.testing.TestUtils
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/**
 * Created by Ferumate on 14.03.2018.
 */
@RunWith(RobolectricTestRunner::class)
class MovieServiceTest {

    private lateinit var movieService: MovieService
    private lateinit var mockedWebServer: MockWebServer

    @Before
    fun init() {
        mockedWebServer = MockWebServer()
        movieService = DependencyProvider
                .retrofit(mockedWebServer.url("/"))
                .create(MovieService::class.java)
    }

    @After
    fun cleanUp() {
        mockedWebServer.shutdown()
    }

    @Test
    fun getMovieList() {
        //given
        val mockResponse = MockResponse()
                .setResponseCode(200)
                .setBody(TestUtils.getResponseFromJson("movie_list_response.json"))
        mockedWebServer.enqueue(mockResponse)

        //when
        val testResult = movieService.getMovieList("").test()

        //then
        testResult.run {
            assertNoErrors()
            assertValueCount(1)
        }
    }
}