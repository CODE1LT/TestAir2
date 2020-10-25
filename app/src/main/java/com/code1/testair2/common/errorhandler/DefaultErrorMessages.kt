package com.code1.testair2.common.errorhandler

import com.code1.testair2.R
import com.code1.testair2.feature.citysearch.presentation.NoCityError
import kotlin.reflect.KClass

object DefaultErrorMessages {
    val messages: MutableMap<KClass<*>, Int> = mutableMapOf(
        Pair(NoCityError::class, R.string.f_city_search_tst_no_city_name),
    )
}