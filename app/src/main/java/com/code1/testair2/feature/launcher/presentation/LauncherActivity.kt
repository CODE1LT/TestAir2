package com.code1.testair2.feature.launcher.presentation

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.code1.testair2.R
import com.code1.testair2.core.ViewModelFactory
import com.code1.testair2.databinding.ActivityLauncherBinding
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

class LauncherActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<LauncherViewModel>

    private lateinit var viewModel: LauncherViewModel

    private lateinit var dataBinding: ActivityLauncherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addReferencesToViewsAndViewModel()
    }

    private fun addReferencesToViewsAndViewModel() {
        Timber.d("addReferencesToViewsAndViewModel()")
        viewModel = ViewModelProviders.of(this, viewModelFactory)[LauncherViewModel::class.java]
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_launcher)
        dataBinding.lifecycleOwner = this
        dataBinding.viewModel = viewModel
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.navHostFragment).navigateUp()

}