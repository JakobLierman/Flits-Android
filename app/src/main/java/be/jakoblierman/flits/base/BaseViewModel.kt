package be.jakoblierman.flits.base

import androidx.lifecycle.ViewModel
import be.jakoblierman.flits.injection.component.DaggerViewModelInjectorComponent
import be.jakoblierman.flits.injection.component.ViewModelInjectorComponent
import be.jakoblierman.flits.injection.module.NetworkModule
import be.jakoblierman.flits.viewmodels.AvgSpeedCheckViewModel
import be.jakoblierman.flits.viewmodels.PoliceCheckViewModel
import be.jakoblierman.flits.viewmodels.SpeedCameraViewModel
import be.jakoblierman.flits.viewmodels.UserViewModel

abstract class BaseViewModel : ViewModel() {

    private val injectorComponent: ViewModelInjectorComponent =
        DaggerViewModelInjectorComponent
            .builder()
            .networkModule(NetworkModule)
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is SpeedCameraViewModel -> injectorComponent.inject(this)
            is AvgSpeedCheckViewModel -> injectorComponent.inject(this)
            is PoliceCheckViewModel -> injectorComponent.inject(this)
            is UserViewModel -> injectorComponent.inject(this)
        }
    }

}