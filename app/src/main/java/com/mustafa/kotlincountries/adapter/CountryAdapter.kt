package com.mustafa.kotlincountries.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.mustafa.kotlincountries.databinding.ItemRowBinding
import com.mustafa.kotlincountries.model.CountryModel
import com.mustafa.kotlincountries.util.uploadInternet
import java.util.ArrayList

class CountryAdapter(var countryList:ArrayList<CountryModel>): RecyclerView.Adapter<CountryAdapter.CountryHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val binding  = ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountryHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.binding.countryName.text = countryList.get(index = position).countryName.toString()
        holder.binding.countryRegion.text = countryList.get(index = position).countryRegion.toString()

      //  Glide.with(holder.binding.root.context).load(countryList.get(index = position).countryImage.toString()).into(holder.binding.countryFlagImage)
        holder.binding.countryFlagImage.uploadInternet(countryList.get(index = position).countryImage.toString(), progressDrawable = placeholderProgressBar(holder.binding.root.context))

    }

    override fun getItemCount(): Int {
       return countryList.size
    }
    inner class CountryHolder( var binding: ItemRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    fun updateCountryList(newCountryList:List<CountryModel>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    fun placeholderProgressBar(context: Context): CircularProgressDrawable {
        return  CircularProgressDrawable(context).apply {
            strokeWidth = 8f
            centerRadius = 40f
            start()
        }
    }



}