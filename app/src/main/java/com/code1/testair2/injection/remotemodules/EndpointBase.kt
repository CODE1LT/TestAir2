package com.code1.testair2.injection.remotemodules

import com.code1.testair2.util.Configuration
import com.code1.testair2.util.network.MOCK_API_URL

@Suppress("unused")
enum class EndpointBase(override val configurationValue: String) : Configuration<EndpointBase> {
    MOCK(MOCK_API_URL),
    DEV("http://api.openweathermap.org/"),
    PROD("http://api.openweathermap.org/")
}