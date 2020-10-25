package com.code1.testair2.feature.citieslist.presentation

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import es.dmoral.toasty.Toasty
import lt.code1.testair.NavigationHost
import lt.code1.testair.R
import lt.code1.testair.base.BaseFragment
import lt.code1.testair.databinding.FragmentCitiesListBinding
import timber.log.Timber


class CitiesListFragment : BaseFragment() {

    private var navigationHost: NavigationHost? = null
    private lateinit var citiesListFragmentViewModel: CitiesListFragmentViewModel
    private lateinit var fragmentCityListBinding: FragmentCitiesListBinding
    override val layoutId = R.layout.fragment_cities_list
    override val viewModelClass = CitiesListFragmentViewModel::class
    private val args: CitiesListFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("onCreate()")
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        Timber.d("onAttach()")
        super.onAttach(context)
        navigationHost = context as NavigationHost
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated()")
        super.onViewCreated(view, savedInstanceState)
        addReferencesToViewsAndViewModel()
        setupActionBar()
        observeNotificationEvent()
        loadCityData()
    }

    private fun addReferencesToViewsAndViewModel() {
        Timber.d("addReferencesToViewsAndViewModel()")
        citiesListFragmentViewModel = getViewModel() as CitiesListFragmentViewModel
        fragmentCityListBinding = viewDataBinding as FragmentCitiesListBinding
    }

    private fun setupActionBar() {
        Timber.d("setupActionBar()")
        (activity as AppCompatActivity).setSupportActionBar(fragmentCityListBinding.fCitiesListToolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        fragmentCityListBinding.fCitiesListToolbar.setNavigationOnClickListener { onUpClick() }
    }

    private fun onUpClick() {
        Timber.d("onUpClick()")
        navigationHost?.onUpClick()
    }

    private fun observeNotificationEvent() {
        Timber.d("observeNotificationEvent()")
        citiesListFragmentViewModel.notificationEvent.observe(
            viewLifecycleOwner,
            Observer { notification ->
                if (!notification.isOperationSuccessfull) {
                    showFailureNotification(notification.unsuccessMessage)
                    navigationHost?.onUpClick()
                }
            })
    }

    private fun showFailureNotification(message: String) {
        Toasty.error(
            requireActivity(),
            message,
            Toast.LENGTH_SHORT,
            true
        )
            .apply {
                setGravity(Gravity.TOP, 0, 80)
                show()
            }
    }

    private fun loadCityData() {
        Timber.d("loadUserData()")
        args.cityName?.let {
            if (it != "") {
                citiesListFragmentViewModel.getCity(it)
            } else {
                citiesListFragmentViewModel.getSearchHistory()
            }
        }
    }

    override fun onDetach() {
        Timber.d("onDetach()")
        super.onDetach()
        navigationHost = null
    }
}
