package com.mustafa.kotlincountries.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit
import java.util.prefs.Preferences

class CustomSharedPrefences {

    companion object{
        private  var sharedPrefences:SharedPreferences? = null
       @Volatile private  var instance:CustomSharedPrefences? = null
        private  val lock= Any()
        operator fun invoke(context: Context): CustomSharedPrefences = instance ?: synchronized(lock){
            instance ?: makeShared(context).also {
                instance = it
            }
        }

        fun makeShared(context: Context): CustomSharedPrefences{
            sharedPrefences = context.getSharedPreferences("TIME",Context.MODE_PRIVATE)

                PreferenceManager.getDefaultSharedPreferences(context)
            // PreferenceManager.getDefaultSharedPreferences(context)
               // context.getSharedPreferences("TİME",Context.MODE_PRIVATE)
            return  CustomSharedPrefences()
        }


    }

    fun saveTime(time:Long){
        sharedPrefences?.edit(commit = true){
            putLong("time",time)
        }
    }

    fun getTime() = sharedPrefences?.getLong("time",0)

}