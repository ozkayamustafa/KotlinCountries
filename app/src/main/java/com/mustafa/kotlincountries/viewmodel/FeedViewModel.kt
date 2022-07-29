package com.mustafa.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mustafa.kotlincountries.model.CountryModel
import com.mustafa.kotlincountries.service.CountryAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class FeedViewModel : ViewModel() {

    var countries = MutableLiveData<List<CountryModel>>()
    var errorCountry = MutableLiveData<Boolean>()
    var countryLoading = MutableLiveData<Boolean>()
    private  val countryAPIService = CountryAPIService
    private  val disposable = CompositeDisposable()

    fun refleshData(){
            getDataFromAPI()
    }

    private  fun getDataFromAPI(){
            countryLoading.value = true
            disposable.add(countryAPIService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CountryModel>>(){
                    override fun onSuccess(t: List<CountryModel>) {
                        countries.value = t
                        countryLoading.value = false
                        errorCountry.value = false
                    }
                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        errorCountry.value = true
                    }

                })

            )
    }


}