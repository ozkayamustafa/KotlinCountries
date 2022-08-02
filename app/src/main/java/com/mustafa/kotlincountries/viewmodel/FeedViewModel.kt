package com.mustafa.kotlincountries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mustafa.kotlincountries.model.CountryModel
import com.mustafa.kotlincountries.service.CountryAPIService
import com.mustafa.kotlincountries.service.CountryDatabase
import com.mustafa.kotlincountries.util.CustomSharedPrefences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.lang.Appendable
import java.util.ArrayList

class FeedViewModel(application: Application) : BaseViewModel(application) {

    var countries = MutableLiveData<List<CountryModel>>()
    var errorCountry = MutableLiveData<Boolean>()
    var countryLoading = MutableLiveData<Boolean>()

    private  var refreshTime = 10*60*1000*1000*1000L

    var customShared = CustomSharedPrefences(getApplication())
    private  val countryAPIService = CountryAPIService
    private  val disposable = CompositeDisposable()

    fun refleshData(){
        val updateTime = customShared.getTime()

        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime){
            getDataSQLite()
        }
        else{
            getDataFromAPI()
        }

    }

    private  fun getDataSQLite(){
        countryLoading.value = true
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            val countryList =  dao.getAllCountry()
            showCountries(countryList)
        }

    }

    fun getDataRefreshAPI(){
        getDataFromAPI()
    }

    private  fun getDataFromAPI(){
            countryLoading.value = true
            disposable.add(countryAPIService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<CountryModel>>(){
                    override fun onSuccess(t: List<CountryModel>) {
                      storeInSQLite(t)
                    }
                    override fun onError(e: Throwable) {
                        countryLoading.value = false
                        errorCountry.value = true
                    }

                })

            )
    }

    private fun showCountries(t:List<CountryModel>){
        countries.value = t
        countryLoading.value = false
        errorCountry.value = false
    }

    private  fun storeInSQLite(t:List<CountryModel>){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
                dao.deleteALlCountry()

            val listLong =  dao.insertAll(*t.toTypedArray())
            var i = 0
            while (i < t.size){
                t[i].uuid = listLong[i].toInt()
                i = i+1
            }
            showCountries(t)
        }
        customShared.saveTime(System.nanoTime())
    }

}