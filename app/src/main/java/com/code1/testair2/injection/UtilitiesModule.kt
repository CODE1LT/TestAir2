package com.code1.testair2.injection

import com.code1.testair2.util.SoftInputHelper
import dagger.Module
import dagger.Provides
import org.threeten.bp.ZoneId
import javax.inject.Singleton

@Module
class UtilitiesModule {

    @Singleton
    @Provides
    fun provideZoneId(): ZoneId = ZoneId.systemDefault()

    @Singleton
    @Provides
    fun provideSoftInputHelper(): SoftInputHelper = SoftInputHelper()
}