package com.mustafa.kotlincountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafa.kotlincountries.R
import com.mustafa.kotlincountries.adapter.CountryAdapter
import com.mustafa.kotlincountries.databinding.FragmentFeedBinding
import com.mustafa.kotlincountries.model.CountryModel
import com.mustafa.kotlincountries.viewmodel.FeedViewModel
import java.util.ArrayList

class FeedFragment : Fragment() {

    private lateinit var binding:FragmentFeedBinding
    private lateinit var viewModel: FeedViewModel
    private  lateinit var  adapterr:CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapterr = CountryAdapter(arrayListOf())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_feed,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       viewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        viewModel.refleshData()

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapterr

        binding.swipeRefleshLayout.setOnRefreshListener {
            binding.recyclerView.visibility = View.GONE
            binding.countryError.visibility = View.GONE
            binding.countryLoading.visibility = View.VISIBLE
            binding.swipeRefleshLayout.isRefreshing = false
            viewModel.getDataRefreshAPI()
        }
        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapterr.updateCountryList(it)
            }
        })

        viewModel.errorCountry.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.recyclerView.visibility = View.GONE
                binding.countryError.visibility = View.VISIBLE
                binding.countryLoading.visibility = View.GONE
            }
            else{
                binding.recyclerView.visibility = View.VISIBLE
                binding.countryError.visibility = View.GONE
                binding.countryLoading.visibility = View.GONE
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {
            if (it){
                binding.recyclerView.visibility = View.GONE
                binding.countryError.visibility = View.GONE
                binding.countryLoading.visibility = View.VISIBLE
            }
            else{
                binding.recyclerView.visibility = View.VISIBLE
                binding.countryError.visibility = View.GONE
                binding.countryLoading.visibility = View.GONE
            }
        })

    }

}