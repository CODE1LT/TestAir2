package com.code1.testair2.feature.citysearch.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.code1.testair2.R
import com.code1.testair2.common.errorhandler.ErrorHandler
import com.code1.testair2.common.events.EventObserver
import com.code1.testair2.core.ViewModelFactory
import com.code1.testair2.databinding.FragmentCitySearchBinding
import com.code1.testair2.feature.citieslist.presentation.CitiesListFragmentArgs
import com.code1.testair2.util.SoftInputHelper
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.activity_launcher.*
import timber.log.Timber
import javax.inject.Inject

class CitySearchFragment : DaggerFragment() {

    private lateinit var binding: FragmentCitySearchBinding

    @Inject
    lateinit var errorHandler: ErrorHandler

    @Inject
    lateinit var citySearchFragmentViewModelFactory: ViewModelFactory<CitySearchFragmentViewModel>

    private val citySearchFragmentViewModel by lazy {
        ViewModelProviders
            .of(this, citySearchFragmentViewModelFactory)
            .get(CitySearchFragmentViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate()")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCitySearchBinding.inflate(inflater)
        binding.viewModel = citySearchFragmentViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated()")
        super.onViewCreated(view, savedInstanceState)
        observeSearchButtonClickedEvent()
        observeHistoryButtonClickedEvent()
    }

    private fun observeSearchButtonClickedEvent() {
        Timber.d("observeUserItemClickedEvent()")
        citySearchFragmentViewModel.cityNameSubmitedEvent.observe(
            viewLifecycleOwner,
            EventObserver { cityName ->
                if (cityName.isNullOrEmpty()) {
                    errorHandler.showError(requireView(), NoCityError())
                } else {
                    SoftInputHelper().hideKeyboard(requireView(), requireActivity())
                    navigateTo(
                        R.id.action_citySearch_to_citiesList,
                        CitiesListFragmentArgs(cityName).toBundle()
                    )
                }
            })
    }

    private fun observeHistoryButtonClickedEvent() {
        Timber.d("observeUserItemClickedEvent()")
        citySearchFragmentViewModel.retrieveSearchHistoryEvent.observe(
            viewLifecycleOwner,
            EventObserver {
                SoftInputHelper().hideKeyboard(requireView(), requireActivity())
                    navigateTo(
                        R.id.action_citySearch_to_citiesList,
                        CitiesListFragmentArgs("").toBundle()
                    )
            })
    }

    private fun navigateTo(id: Int, bundle: Bundle? = null) {
        findNavController().navigate(id, bundle)
    }
}
