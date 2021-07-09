package com.ihavenodomain.quotesterminal.quoteslist.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ihavenodomain.quotesterminal.quoteslist.data.model.watchsecurities.Q
import com.ihavenodomain.quotesterminal.quoteslist.domain.interactors.QuotesInteractor
import com.ihavenodomain.quotesterminal.quoteslist.presentation.viewstate.Error
import com.ihavenodomain.quotesterminal.quoteslist.presentation.viewstate.QuotesListState
import com.ihavenodomain.quotesterminal.quoteslist.presentation.viewstate.Errors
import com.ihavenodomain.quotesterminal.quoteslist.presentation.viewstate.Loading
import com.ihavenodomain.quotesterminal.quoteslist.presentation.viewstate.ShowList
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.net.UnknownHostException

class SecuritiesListMainViewModel(
    private val quotesInteractor: QuotesInteractor,
): ViewModel() {

    private val quotesListStateLiveData: MutableLiveData<QuotesListState> = MutableLiveData()
    fun observeScreenState(): LiveData<QuotesListState> = quotesListStateLiveData

    private val disposables = CompositeDisposable()

    init {
        requestInitialQuotesList()
    }

    private fun requestInitialQuotesList() {
        setState(Loading)

        quotesInteractor.getBaseSecuritiesTickersList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ securities ->
                setState(ShowList(securities))
            }, { throwable ->
                when (throwable) {
                    is UnknownHostException -> { setState(Error(Errors.UNKNOWN_HOST)) }
                    else -> { setState(Error(Errors.OTHER)) }
                }
            })
            .also { disposables.add(it) }
    }

    private fun setState(state: QuotesListState) {
        quotesListStateLiveData.postValue(state)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}