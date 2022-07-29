package com.mustafa.kotlincountries.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mustafa.kotlincountries.model.CountryModel
import com.mustafa.kotlincountries.service.CountryDAO
import com.mustafa.kotlincountries.service.CountryDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryViewModel:ViewModel() {

    val countries = MutableLiveData<CountryModel>()
    val coutryDao = MutableLiveData<CountryDAO>()
    fun getDataFromRoom(context: Context){
        // Room database kaydedecek
        coutryDao.value = CountryDatabase.invoke(context = context).countryDao()




    }


}