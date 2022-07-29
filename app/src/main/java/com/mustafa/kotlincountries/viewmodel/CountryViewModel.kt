package com.mustafa.kotlincountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mustafa.kotlincountries.model.CountryModel

class CountryViewModel:ViewModel() {

    val countries = MutableLiveData<CountryModel>()

    fun getDataFromRoom(){
        // Room database kaydedecek
    }


}