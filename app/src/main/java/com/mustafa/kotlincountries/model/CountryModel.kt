package com.mustafa.kotlincountries.model

import com.google.gson.annotations.SerializedName

data class CountryModel(
    @SerializedName(value = "name")
    val countryName:String?,

    @SerializedName(value = "region")
    val countryRegion:String?,

    @SerializedName(value = "capital")
    val countryCapital:String?,

    @SerializedName(value = "currency")
    val countryCurreny:String?,

    @SerializedName(value = "language")
    val countryLanguage:String?,

    @SerializedName(value = "flag")
    val countryImage:String?

)