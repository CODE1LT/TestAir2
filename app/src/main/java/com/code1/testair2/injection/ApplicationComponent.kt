package com.code1.testair2.injection

import com.code1.testair2.common.LoggerModule
import com.code1.testair2.common.NetworkErrorFormatterModule
import com.code1.testair2.injection.remotemodules.NetworkModule
import com.code1.testair2.util.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

//Keep modules alphabetically sorted
@Singleton
@Component(
    modules = [
        ActivityBuilder::class,
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        DatabaseModule::class,
        LoggerModule::class,
        NetworkErrorFormatterModule::class,
        NetworkModule::class,
        UtilitiesModule::class,
    ]
)
interface ApplicationComponent : AndroidInjector<Application> {

    override fun inject(app: Application)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

}