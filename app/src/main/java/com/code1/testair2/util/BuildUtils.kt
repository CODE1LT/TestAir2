package com.code1.testair2.util

import com.code1.testair2.BuildConfig
import com.code1.testair2.common.BuildTypes
import com.code1.testair2.common.ServicesDimension

interface Configuration<T : Enum<T>> {
    val configurationValue: String
}

@Suppress("unused")
inline fun <reified T> get(): String where T : Configuration<T>, T : Enum<T> = when {
    (BuildConfig.BUILD_TYPE == BuildTypes.MOCK.value) ->
        enumValueOf<T>(ServicesDimension.MOCK.name).configurationValue
    (BuildConfig.BUILD_TYPE == BuildTypes.TEST.value) ->
        enumValueOf<T>(ServicesDimension.DEV.name).configurationValue
    (BuildConfig.BUILD_TYPE == BuildTypes.DEBUG.value) ->
        enumValueOf<T>(ServicesDimension.DEV.name).configurationValue
    (BuildConfig.BUILD_TYPE == BuildTypes.RELEASE.value) ->
        enumValueOf<T>(ServicesDimension.PROD.name).configurationValue
    else -> enumValueOf<T>(ServicesDimension.DEV.name).configurationValue
}