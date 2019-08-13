package be.jakoblierman.flits.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import be.jakoblierman.flits.api.FlitsApi
import be.jakoblierman.flits.base.BaseViewModel
import be.jakoblierman.flits.model.PoliceCheck
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PoliceCheckViewModel : BaseViewModel() {

    val policeCheck = MutableLiveData<PoliceCheck>()
    val policeChecks = MutableLiveData<List<PoliceCheck>>()
    val loadingVisibility = MutableLiveData<Int>()
    val objectVisibility = MutableLiveData<Int>()

    @Inject
    lateinit var flitsApi: FlitsApi
    private var disposables = CompositeDisposable()

    init {
        disposables.add(
            flitsApi.getPoliceChecks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveStart() }
                .doOnTerminate { onRetrieveFinish() }
                .subscribe(
                    { results -> onRetrieveListSuccess(results) },
                    { error -> onRetrieveError(error) }
                )
        )
    }

    private fun onRetrieveListSuccess(results: List<PoliceCheck>) {
        policeChecks.value = results
        Logger.i(results.toString())
    }

    fun getPoliceCheckById(id: String) {
        disposables.add(
            flitsApi.getPoliceCheckById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveStart() }
                .doOnTerminate { onRetrieveFinish() }
                .subscribe(
                    { result -> onRetrieveSingleSuccess(result) },
                    { error -> onRetrieveError(error) }
                )
        )
    }

    private fun onRetrieveError(error: Throwable) {
        Logger.e(error.message!!)
    }

    private fun onRetrieveSingleSuccess(result: PoliceCheck) {
        policeCheck.value = result
        Logger.i(result.toString())
    }

    private fun onRetrieveFinish() {
        loadingVisibility.value = View.GONE
        objectVisibility.value = View.VISIBLE
    }

    private fun onRetrieveStart() {
        loadingVisibility.value = View.VISIBLE
        objectVisibility.value = View.GONE
    }

    fun postPoliceCheck(policeCheck: PoliceCheck) {
        disposables.add(
            flitsApi.postPoliceCheck(policeCheck)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveStart() }
                .doOnTerminate { onRetrieveFinish() }
                .subscribe(
                    { result -> onRetrieveSingleSuccess(result) },
                    { error -> onRetrieveError(error) }
                )
        )
    }

    fun deletePoliceCheck() {
        disposables.add(
            flitsApi.deletePoliceCheck(policeCheck.value!!.id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveStart() }
                .doOnTerminate { onRetrieveFinish() }
                .subscribe(
                    { result -> onRetrieveDeleteSuccess(result) },
                    { error -> onRetrieveError(error) }
                )
        )
    }

    private fun onRetrieveDeleteSuccess(result: Boolean) {
        // TODO
        //policeCheck.value = result
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
