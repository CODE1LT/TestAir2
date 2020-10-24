package com.code1.testair2.util.network

import android.content.Context
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection
import javax.inject.Inject

private const val RESPONSE_FILES_PATH = "api-response"
private const val RESPONSE_GET_WEATHER_DATA =
    "response_get_weather_data.json"
private const val PARAMETERS_DELIMITER = "?"
private const val FOLDER_DELIMITER = "/"
private const val REQUESTED_RESOURCE_COULD_NOT_BE_FOUND = 404
const val WEATHER_DATA_PATH = "/weather"

class RequestDispatcher @Inject constructor() {

    fun get(context: Context) = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            val rootPath = (request.path)?.substringBefore(PARAMETERS_DELIMITER) ?: ""
            return when (getResourceName(rootPath)) {
                getResourceName(WEATHER_DATA_PATH) -> getMockResponse(
                    context,
                    "$RESPONSE_FILES_PATH/$RESPONSE_GET_WEATHER_DATA"
                )
                else -> MockResponse().setResponseCode(REQUESTED_RESOURCE_COULD_NOT_BE_FOUND)
            }
        }
    }

    private fun getResourceName(resourcePath: String) =
        resourcePath.substringAfterLast(FOLDER_DELIMITER)

    @Suppress("SameParameterValue")
    private fun getMockResponse(context: Context, path: String) = MockResponse()
        .setResponseCode(HttpURLConnection.HTTP_OK)
        .setBody(getUsersListFromFile(context, path))

    private fun getUsersListFromFile(context: Context, path: String) = context.assets
        .open(path)
        .bufferedReader()
        .use { it.readText() }
}