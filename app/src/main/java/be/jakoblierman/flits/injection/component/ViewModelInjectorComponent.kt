package be.jakoblierman.flits.injection.component

import be.jakoblierman.flits.injection.module.NetworkModule
import be.jakoblierman.flits.viewmodels.AvgSpeedCheckViewModel
import be.jakoblierman.flits.viewmodels.PoliceCheckViewModel
import be.jakoblierman.flits.viewmodels.SpeedCameraViewModel
import be.jakoblierman.flits.viewmodels.UserViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelInjectorComponent {

    fun inject(speedCameraViewModel: SpeedCameraViewModel)
    fun inject(avgSpeedCheckViewModel: AvgSpeedCheckViewModel)
    fun inject(policeCheckViewModel: PoliceCheckViewModel)
    fun inject(userViewModel: UserViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjectorComponent

        fun networkModule(networkModule: NetworkModule): Builder
    }

}