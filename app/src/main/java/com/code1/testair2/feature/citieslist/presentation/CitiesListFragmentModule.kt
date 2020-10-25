@file:Suppress("unused")
package com.code1.testair2.feature.citieslist.presentation

import androidx.lifecycle.ViewModel
import com.code1.testair2.feature.citieslist.domain.CitiesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import com.code1.testair2.feature.citieslist.domain.model.CityInlinedDomainModel
import com.code1.testair2.feature.citieslist.domain.usecase.*
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
            : RetrieveSingleInteractorWithParams<String, Resource<@JvmSuppressWildcards List<CityInlinedDomainModel>>>

    @Binds
    abstract fun provideGetCitiesListInteractor(getCitiesListInteractor: GetCitiesListInteractor)
            : RetrieveSingleInteractor<Resource<@JvmSuppressWildcards List<CityInlinedDomainModel>>>

    @Binds
    abstract fun provideCitiesListMapper(
        citiesListMapper: CitiesListMapper
    ): @JvmSuppressWildcards Function1<@JvmSuppressWildcards List<CitiesListEntity>, @JvmSuppressWildcards List<CityInlinedDomainModel>>

    @Provides
    fun provideGetCitiesListUseCase(citiesRepository: CitiesRepository): GetCitiesListUseCase =
        GetCitiesListUseCaseImpl(citiesRepository)

}