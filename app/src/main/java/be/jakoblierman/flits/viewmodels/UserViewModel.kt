package be.jakoblierman.flits.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import be.jakoblierman.flits.api.FlitsApi
import be.jakoblierman.flits.base.BaseViewModel
import be.jakoblierman.flits.model.User
import com.orhanobut.logger.Logger
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class UserViewModel : BaseViewModel() {

    val user = MutableLiveData<User>()
    val loggedInUser = MutableLiveData<User>()
    val loadingVisibility = MutableLiveData<Int>()

    @Inject
    lateinit var flitsApi: FlitsApi
    private var disposables = CompositeDisposable()

    fun register(fullName: String, email: String, password: String): User {
        // TODO: register
    }

    fun login(email: String, password: String): User? {
        // TODO: login
        return null
    }

    fun isUniqueEmail(email: String): Boolean {
        // TODO: isUniqueEmail
        return false
    }

    fun logout() {
        // TODO: revoke authentication
    }

    private fun onRetrieveFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrieveStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrieveError(error: Throwable) {
        Logger.e(error.message!!)
    }

    private fun onRetrieveSingleSuccess(result: User) {
        user.value = result
        Logger.i(result.toString())
    }

    /**
     * Disposes the subscription when the [BaseViewModel] is no longer used.
     */
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}