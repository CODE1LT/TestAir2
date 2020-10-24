package com.code1.testair2.util.network

import android.content.Context
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Inject

const val SERVER_PORT = 8080
const val MOCK_API_URL = "http://localhost:$SERVER_PORT/"

class MockWebServer @Inject constructor(
    private val requestDispatcher: RequestDispatcher
) {
    fun start(context: Context) {
        Thread(Runnable {
            MockWebServer().run {
                dispatcher = requestDispatcher.get(context)
                start(SERVER_PORT)
            }
        }).run {
            start()
            join()
        }
    }

}