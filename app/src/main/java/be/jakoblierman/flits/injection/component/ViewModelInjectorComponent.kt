package be.jakoblierman.flits.injection.component

import be.jakoblierman.flits.injection.module.NetworkModule
import be.jakoblierman.flits.viewmodels.AvgSpeedCheckViewModel
import be.jakoblierman.flits.viewmodels.PoliceCheckViewModel
import be.jakoblierman.flits.viewmodels.SpeedCameraViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjectorComponent {

    fun inject(speedCameraViewModel: SpeedCameraViewModel)
    fun inject(avgSpeedCheckViewModel: AvgSpeedCheckViewModel)
    fun inject(policeCheckViewModel: PoliceCheckViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjectorComponent

        fun networkModule(networkModule: NetworkModule): Builder
    }

}