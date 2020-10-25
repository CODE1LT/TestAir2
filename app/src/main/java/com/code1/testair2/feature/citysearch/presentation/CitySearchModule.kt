package com.code1.testair2.feature.citysearch.presentation

import com.code1.testair2.common.errorhandler.DefaultErrorHandler
import com.code1.testair2.common.errorhandler.ErrorHandler
import dagger.Module
import dagger.Provides

@Module
class CitySearchModule {

    @Provides
    fun provideErrorHandler(): ErrorHandler = DefaultErrorHandler()
}