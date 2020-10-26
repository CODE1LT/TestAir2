package com.code1.testair2.injection

import com.code1.testair2.feature.launcher.presentation.LauncherActivity
import com.code1.testair2.feature.launcher.presentation.LauncherActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [LauncherActivityModule::class])
    abstract fun bindLauncherActivity(): LauncherActivity
}