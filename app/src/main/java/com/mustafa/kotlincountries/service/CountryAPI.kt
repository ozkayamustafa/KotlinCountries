package com.mustafa.kotlincountries.service

import com.mustafa.kotlincountries.model.CountryModel
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET

interface CountryAPI {

      // BASE_URL --> https://raw.githubusercontent.com/
      //
      // atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json
    @GET("atilsamancioglu/IA19-DataSetCountries/master/countrydataset.json")
    fun getCountriesData(): Single<List<CountryModel>>

}