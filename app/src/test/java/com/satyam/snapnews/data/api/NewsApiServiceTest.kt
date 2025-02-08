package com.satyam.snapnews.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsApiServiceTest {
    private lateinit var service:NewsApiService
    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    private fun enqueueMockResponse(
        fileName:String
    ){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)

    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun getTopHeadlines_sentRequest_receivedExpected() {
       runBlocking {
           enqueueMockResponse("newsresponse.json")
           val responseBody = service.getTopHeadlines("us",1).body()
           val request = server.takeRequest()
           assertThat(responseBody).isNotNull()
           assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=aa95f5fad86448c19a9a00dc34e1d121")
       }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctPagesSize(){
        runBlocking{
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us",1).body()
            val articlesList = responseBody!!.articles
            assertThat(articlesList.size).isEqualTo(19)
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent(){
        runBlocking{
            enqueueMockResponse("newsresponse.json")
            val responseBody = service.getTopHeadlines("us",1).body()
            val articlesList = responseBody!!.articles
            val article = articlesList[0]
            assertThat(article.author).isEqualTo("Derek Saul")
            assertThat(article.url).isEqualTo("https://www.forbes.com/sites/dereksaul/2025/02/03/apple-nvidia-and-tesla-among-hardest-hit-as-tariffs-drag-down-stock-market/")
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_noNullFields() = runBlocking {
        enqueueMockResponse("newsresponse.json")
        val responseBody = service.getTopHeadlines("us", 1).body()
        assertThat(responseBody).isNotNull()

        val articlesList = responseBody!!.articles
        assertThat(articlesList).isNotEmpty()

        for (article in articlesList) {
            assertThat(article.title).isNotNull()
            assertThat(article.url).isNotNull()
            assertThat(article.author).isNotNull()
        }
    }


}