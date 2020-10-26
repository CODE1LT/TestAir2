package com.code1.testair2.feature.citieslist.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.code1.testair2.R
import com.code1.testair2.common.errorhandler.ErrorHandler
import com.code1.testair2.common.events.EventObserver
import com.code1.testair2.core.ViewModelFactory
import com.code1.testair2.databinding.FragmentCitiesListBinding
import com.code1.testair2.feature.citieslist.presentation.list.CitiesListAdapter
import com.code1.testair2.feature.citieslist.presentation.list.MarginItemDecoration
import com.code1.testair2.feature.launcher.presentation.LauncherViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_cities_list.*
import timber.log.Timber
import javax.inject.Inject


class CitiesListFragment : DaggerFragment() {

    private val parentViewModel by activityViewModels<LauncherViewModel>()

    private lateinit var binding: FragmentCitiesListBinding

    @Inject
    lateinit var application: Context

    @Inject
    lateinit var errorHandler: ErrorHandler

    @Inject
    lateinit var citiesListFragmentViewModelFactory: ViewModelFactory<CitiesListFragmentViewModel>

    private val citiesListFragmentViewModel by lazy {
        ViewModelProviders
            .of(this, citiesListFragmentViewModelFactory)
            .get(CitiesListFragmentViewModel::class.java)
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
        binding = FragmentCitiesListBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated()")
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
        setupRecyclerView()
        observeCitiesList()
        observeOnError()
        observeLoadingState()
        loadCityData()
    }

    private fun setupActionBar() {
        Timber.d("setupActionBar()")
        (activity as AppCompatActivity).setSupportActionBar(binding.fCitiesListToolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.fCitiesListToolbar.setNavigationOnClickListener { onUpClick() }
    }

    private fun onUpClick() {
        Timber.d("onUpClick()")
        findNavController().navigateUp()
    }

    private fun setupRecyclerView() {
        resources.getDimension(R.dimen.rv_default_padding)
            .toInt()
            .let { MarginItemDecoration(it) }
            .let { f_cities_list_recycler_view.addItemDecoration(it) }
        f_cities_list_recycler_view.adapter = CitiesListAdapter(application)
    }

    private fun observeCitiesList() {
        Timber.d("observeCitiesList()")
        citiesListFragmentViewModel.cityList.observe(
            viewLifecycleOwner,
            { cityList ->
                (f_cities_list_recycler_view.adapter as CitiesListAdapter).submitList(cityList)
            })
    }

    private fun observeOnError() {
        Timber.d("observeOnError()")
        citiesListFragmentViewModel.onError.observe(
            viewLifecycleOwner,
            EventObserver { exception ->
                errorHandler.showError(requireView(), exception.message ?: "")
                findNavController().navigateUp()
            })
    }

    private fun observeLoadingState() {
        Timber.d("observeLoadingState()")
        citiesListFragmentViewModel.loading.observe(
            viewLifecycleOwner,
            { isLoading ->
                if (isLoading) {
                    parentViewModel.showLoading()
                } else {
                    parentViewModel.hideLoading()
                }
            })
    }

    private fun loadCityData() {
        Timber.d("loadCityData()")
        CitiesListFragmentArgs.fromBundle(this.requireArguments()).cityName?.let {
            if (it != "") {
                citiesListFragmentViewModel.getCity()
            } else {
                citiesListFragmentViewModel.getSearchHistory()
            }
        }
    }
}
