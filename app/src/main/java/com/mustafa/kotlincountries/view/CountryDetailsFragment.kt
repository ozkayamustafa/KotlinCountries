package com.mustafa.kotlincountries.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.mustafa.kotlincountries.R
import com.mustafa.kotlincountries.databinding.FragmentCountryDetailsBinding
import com.mustafa.kotlincountries.util.uploadInternet
import com.mustafa.kotlincountries.viewmodel.CountryViewModel


class CountryDetailsFragment : Fragment() {

        private  lateinit var binding: FragmentCountryDetailsBinding
        private  lateinit var  viewmodel:CountryViewModel
        private  var counryID = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_country_details,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            counryID = CountryDetailsFragmentArgs.fromBundle(it).uuID
        }
        viewmodel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewmodel.getDataFromRoom(counryID)
        observeDataLive(binding.root.context)
    }

    private fun observeDataLive(context: Context){
        viewmodel.countries.observe(viewLifecycleOwner){
          /*  binding.countryDetailImage.uploadInternet(it.countryImage, progressDrawable = progressDrawble(context))
            binding.countryName.text = it.countryName
            binding.countryLanguage.text = it.countryLanguage
            binding.countryCapital.text = it.countryCapital
            binding.countryRegion.text = it.countryRegion*/
            binding.countryDetail = it
        }

    }

   /* private  fun progressDrawble(context: Context):CircularProgressDrawable{
        return  CircularProgressDrawable(context).apply {
            strokeWidth = 8F
            centerRadius= 40F
            start()
        }
    }*/

}