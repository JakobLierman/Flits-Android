package be.jakoblierman.flits.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import be.jakoblierman.flits.api.FlitsApi
import be.jakoblierman.flits.base.BaseViewModel
import be.jakoblierman.flits.model.User
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject
import javax.security.auth.login.LoginException

class UserViewModel : BaseViewModel() {

    val user = MutableLiveData<User>()
    val loggedInUser = MutableLiveData<User>()
    val loadingVisibility = MutableLiveData<Int>()
    val contentEnabled = MutableLiveData<Boolean>()
    val validEmail = MutableLiveData<Boolean>()

    @Inject
    lateinit var flitsApi: FlitsApi
    private var disposables = CompositeDisposable()

    init {
        loadingVisibility.value = View.GONE
        contentEnabled.value = true
    }

    fun register(fullName: String, email: String, password: String) {
        try {
            loggedInUser.value = flitsApi.register(fullName, email, password).blockingSingle()
        } catch (e: Exception) {
            throw LoginException((e as HttpException).response()!!.errorBody()!!.string())
        }
    }

    fun login(email: String, password: String) {
        try {
            loggedInUser.value = flitsApi.login(email, password).blockingSingle()
        } catch (e: Exception) {
            throw LoginException((e as HttpException).response()!!.errorBody()!!.string())
        }
    }

    fun isValidEmail(email: String): Boolean {
        return flitsApi.isValidEmail(email)
            .doOnSubscribe { onRetrieveStart() }
            .doOnTerminate { onRetrieveFinish() }
            .blockingSingle()
    }

    fun isValidEmailASync(email: String) {
        disposables.add(
            flitsApi.isValidEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveStart() }
                .doOnTerminate { onRetrieveFinish() }
                .subscribe(
                    { results -> onRetrieveValidEmailSuccess(results) },
                    { error -> onRetrieveError(error) }
                )
        )
    }

    private fun onRetrieveValidEmailSuccess(result: Boolean) {
        validEmail.value = result
        Logger.i(result.toString())
    }

    fun logout() {
        TODO("revoke authentication")
    }

    private fun onRetrieveFinish() {
        loadingVisibility.value = View.GONE
        contentEnabled.value = true
    }

    private fun onRetrieveStart() {
        loadingVisibility.value = View.VISIBLE
        contentEnabled.value = false
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