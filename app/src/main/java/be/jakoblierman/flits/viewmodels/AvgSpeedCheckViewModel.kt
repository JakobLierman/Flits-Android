package be.jakoblierman.flits.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import be.jakoblierman.flits.api.FlitsApi
import be.jakoblierman.flits.base.BaseViewModel
import be.jakoblierman.flits.model.AvgSpeedCheck
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AvgSpeedCheckViewModel : BaseViewModel() {

    val avgSpeedCheck = MutableLiveData<AvgSpeedCheck>()
    val avgSpeedChecks = MutableLiveData<List<AvgSpeedCheck>>()
    val loadingVisibility = MutableLiveData<Int>()
    val objectVisibility = MutableLiveData<Int>()

    @Inject
    lateinit var flitsApi: FlitsApi
    private var disposables = CompositeDisposable()

    init {
        disposables.add(
            flitsApi.getAvgSpeedChecks()
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

    private fun onRetrieveListSuccess(results: List<AvgSpeedCheck>) {
        avgSpeedChecks.value = results
        Logger.i(results.toString())
    }

    fun getAvgSpeedCheckById(id: String) {
        disposables.add(
            flitsApi.getAvgSpeedCheckById(id)
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

    private fun onRetrieveSingleSuccess(result: AvgSpeedCheck) {
        avgSpeedCheck.value = result
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

    /**
     * Disposes the subscription when the [BaseViewModel] is no longer used.
     */
    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}