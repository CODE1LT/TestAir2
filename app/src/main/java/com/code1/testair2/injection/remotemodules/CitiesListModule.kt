package com.code1.testair2.injection.remotemodules

import com.code1.testair2.common.Database
import com.code1.testair2.feature.citieslist.data.CitiesRepositoryImpl
import com.code1.testair2.feature.citieslist.data.local.CitiesDao
import com.code1.testair2.feature.citieslist.data.local.CityDataSource
import com.code1.testair2.feature.citieslist.data.remote.GetCitiesListService
import com.code1.testair2.feature.citieslist.data.remote.GetCityService
import com.code1.testair2.feature.citieslist.domain.CitiesRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class CitiesListModule {

    @Singleton
    @Provides
    fun provideCitiesDao(database: Database): CitiesDao =
        database.citiesDao()

    @Singleton
    @Provides
    fun provideCityDataSource(citiesDao: CitiesDao): CityDataSource =
        CityDataSource(citiesDao)

    @Singleton
    @Provides
    fun provideCitiesRepository(
        cityDataSource: CityDataSource,
        getCityService: GetCityService,
        getCitiesListService: GetCitiesListService
    ): CitiesRepository = CitiesRepositoryImpl(cityDataSource, getCityService, getCitiesListService)

    @Singleton
    @Provides
    fun provideGetCityService(@Named(KEY_NETWORKING_MODULE) retrofit: Retrofit): GetCityService =
        retrofit.create(GetCityService::class.java)

    @Singleton
    @Provides
    fun provideGetCitiesListService(@Named(KEY_NETWORKING_MODULE) retrofit: Retrofit): GetCitiesListService =
        retrofit.create(GetCitiesListService::class.java)
}