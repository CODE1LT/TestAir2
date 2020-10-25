package com.code1.testair2.feature.launcher.presentation

import com.code1.testair2.feature.citysearch.CitySearchFragment
import com.code1.testair2.feature.citysearch.CitySearchModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LauncherActivityModule {

    @ContributesAndroidInjector(modules = [CitySearchModule::class])
    abstract fun bindCitySearchFragment(): CitySearchFragment

}