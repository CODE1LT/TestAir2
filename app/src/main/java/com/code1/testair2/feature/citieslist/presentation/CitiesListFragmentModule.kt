@file:Suppress("unused")
package com.code1.testair2.feature.citieslist.presentation

import com.code1.testair2.common.errorhandler.DefaultErrorHandler
import com.code1.testair2.common.errorhandler.ErrorHandler
import com.code1.testair2.feature.citieslist.domain.CitiesRepository
import dagger.Module
import dagger.Provides
import com.code1.testair2.feature.citieslist.domain.usecase.*

@Module
class CitiesListFragmentModule {

    @Provides
    fun provideErrorHandler(): ErrorHandler = DefaultErrorHandler()

    @Provides
    fun provideDeviceDomainModel(fragment: CitiesListFragment): String? =
        CitiesListFragmentArgs.fromBundle(fragment.requireArguments()).cityName

    @Provides
    fun provideFetchCityUseCase(citiesRepository: CitiesRepository): FetchCityUseCase =
        FetchCityUseCaseImpl(citiesRepository)


    @Provides
    fun provideGetCitiesListUseCase(citiesRepository: CitiesRepository): GetCitiesListUseCase =
        GetCitiesListUseCaseImpl(citiesRepository)

}