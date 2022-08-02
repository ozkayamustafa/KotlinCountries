package com.mustafa.kotlincountries.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mustafa.kotlincountries.model.CountryModel
import com.mustafa.kotlincountries.service.CountryDAO
import com.mustafa.kotlincountries.service.CountryDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryViewModel(application: Application):BaseViewModel(application) {

    val countries = MutableLiveData<CountryModel>()



    fun getDataFromRoom(uuid:Int){
        // Room database kaydedecek
            launch {
                 val dao = CountryDatabase(getApplication()).countryDao()
                 val country = dao.getSelectCountry(uuid)
                    countries.value = country
            }
    }


}