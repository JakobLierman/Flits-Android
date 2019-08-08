package be.jakoblierman.flits.viewmodels

import androidx.lifecycle.ViewModel
import be.jakoblierman.flits.model.SpeedCamera

class SpeedCameraViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    //private val _speedCamera = MutableLiveData<SpeedCamera>()
    //
    //val speedCamera: LiveData<SpeedCamera>
    //    get() = _speedCamera

    val speedCamera = SpeedCamera("location", "kind")

}
