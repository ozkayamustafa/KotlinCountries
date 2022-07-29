package com.mustafa.kotlincountries.service

import com.mustafa.kotlincountries.model.CountryModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryAPIService {


        companion object Instance{
            private  val BASE_URL = "https://raw.githubusercontent.com/"
            private  var retrofit: Retrofit? = null

            fun getInstance():Retrofit{

                if (retrofit == null){
                    retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                }
                return retrofit as Retrofit
            }
            fun getData():Single<List<CountryModel>>{
                return  CountryAPIService.Instance.getInstance().create(CountryAPI::class.java).getCountriesData()
            }

        }



}