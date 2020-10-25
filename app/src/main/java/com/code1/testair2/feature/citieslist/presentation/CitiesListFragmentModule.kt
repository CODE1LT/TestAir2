@file:Suppress("unused")
package com.code1.testair2.feature.citieslist.presentation

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import lt.code1.testair.archextensions.ViewModelKey
import lt.code1.testair.datalayer.cities.entities.CitiesListEntity
import lt.code1.testair.datalayer.core.Resource
import lt.code1.testair.domain.RetrieveSingleInteractor
import lt.code1.testair.domain.RetrieveSingleInteractorWithParams
import lt.code1.testair.features.citieslist.data.City
import lt.code1.testair.features.citieslist.interactors.CitiesListMapper
import lt.code1.testair.features.citieslist.interactors.FetchCityInteractor
import lt.code1.testair.features.citieslist.interactors.GetCitiesListInteractor
import javax.inject.Named

@Module
abstract class CitiesListFragmentModule {

    @Module
    companion object {
        private const val CITIES_LIST_FRAGMENT_VIEW_MODEL = "CITIES_LIST_FRAGMENT_VIEW_MODEL"

        @Provides
        @JvmStatic
        @Named(CITIES_LIST_FRAGMENT_VIEW_MODEL)
        fun provideFragmentViewModel(citiesListFragmentViewModel: CitiesListFragmentViewModel)
                : CitiesListFragmentViewModel {
            return citiesListFragmentViewModel
        }
    }

    @Binds
    @IntoMap
    @ViewModelKey(CitiesListFragmentViewModel::class)
    abstract fun provideViewModel(@Named(CITIES_LIST_FRAGMENT_VIEW_MODEL) citiesListFragmentViewModel: CitiesListFragmentViewModel): ViewModel

    @Binds
    abstract fun provideFetchInteractor(fetchCityInteractor: FetchCityInteractor)
            : RetrieveSingleInteractorWithParams<String, Resource<@JvmSuppressWildcards List<City>>>

    @Binds
    abstract fun provideGetCitiesListInteractor(getCitiesListInteractor: GetCitiesListInteractor)
            : RetrieveSingleInteractor<Resource<@JvmSuppressWildcards List<City>>>

    @Binds
    abstract fun provideCitiesListMapper(
        citiesListMapper: CitiesListMapper
    ): @JvmSuppressWildcards Function1<@JvmSuppressWildcards List<CitiesListEntity>, @JvmSuppressWildcards List<City>>

}