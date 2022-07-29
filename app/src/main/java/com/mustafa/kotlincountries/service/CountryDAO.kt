package com.mustafa.kotlincountries.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mustafa.kotlincountries.model.CountryModel

@Dao
interface CountryDAO {

    @Insert
    suspend fun insertAll(vararg country:CountryModel):List<Long>

    @Query("SELECT * FROM CountryModel")
    suspend fun getAllCountry():List<CountryModel>


    @Query("SELECT * FROM CountryModel WHERE uuid = :countryId ")
    suspend fun getSelectCountry(countryId: Int):CountryModel




}