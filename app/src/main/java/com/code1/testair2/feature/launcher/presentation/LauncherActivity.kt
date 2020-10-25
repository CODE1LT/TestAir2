package com.code1.testair2.feature.launcher.presentation

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.code1.testair2.R
import com.code1.testair2.core.ViewModelFactory
import com.code1.testair2.databinding.ActivityLauncherBinding
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_launcher.*
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
        dataBinding.viewModel = viewModel
    }

    fun onUpClick() {
        Timber.d("onUpClick()")
        onSupportNavigateUp()
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.navHostFragment).navigateUp()

}