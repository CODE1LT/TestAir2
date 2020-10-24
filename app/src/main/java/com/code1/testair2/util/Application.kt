package com.code1.testair2.util

import com.code1.testair2.BuildConfig
import com.code1.testair2.common.BuildTypes
import com.code1.testair2.common.Logger
import com.code1.testair2.injection.ApplicationComponent
import com.code1.testair2.injection.DaggerApplicationComponent
import com.code1.testair2.util.network.MockWebServer
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Inject


class Application : DaggerApplication() {

    @Inject
    lateinit var logger: Logger

    @Inject
    lateinit var mockWebServer: MockWebServer

    lateinit var applicationComponent: ApplicationComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder()
            .application(this)
            .build().also {
                applicationComponent = it
            }
    }

    override fun onCreate() {
        super.onCreate()
        logger.init()
        if (BuildConfig.BUILD_TYPE == BuildTypes.MOCK.value) {
            mockWebServer.start(this)
        }
    }

}