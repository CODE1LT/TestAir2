package com.code1.testair2.common

import android.util.Log
import com.code1.testair2.BuildConfig
import dagger.Module
import dagger.Provides
import timber.log.Timber
import javax.inject.Inject

@Module
class LoggerModule {

    @Provides
    fun provideLogger(): Logger {
        return LocalAndRemoteLogger()
    }
}

interface Logger {
    fun init()
}

class LocalAndRemoteLogger @Inject constructor() : Logger {
    override fun init() {
        @Suppress("ConstantConditionIf")
        if (BuildConfig.BUILD_TYPE == BuildTypes.MOCK.value || BuildConfig.BUILD_TYPE == BuildTypes.DEBUG.value) {
            Timber.plant(Timber.DebugTree())
        } else if (BuildConfig.BUILD_TYPE == BuildTypes.TEST.value) {
            Timber.plant(Timber.DebugTree())
            Timber.plant(RemoteLogger())
        } else if (BuildConfig.BUILD_TYPE == BuildTypes.RELEASE.value) {
            Timber.plant(RemoteLogger())
        }
    }
}

class RemoteLogger @Inject constructor() : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
        super.log(priority, tag, message, throwable)
        if (priority == Log.ERROR || priority == Log.DEBUG) {
// Uncomment, when crashlytics is configured
//            FirebaseCrashlytics.getInstance().log(message)
//            if (throwable != null) {
//                FirebaseCrashlytics.getInstance().recordException(throwable)
//            }
        } else return
    }
}



