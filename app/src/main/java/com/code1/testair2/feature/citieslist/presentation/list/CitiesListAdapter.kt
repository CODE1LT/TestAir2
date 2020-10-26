package com.code1.testair2.feature.citieslist.presentation.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.code1.testair2.R
import com.code1.testair2.databinding.ItemCityBinding
import com.code1.testair2.feature.citieslist.domain.model.CityInlinedDomainModel

class CitiesListAdapter (
    private val context: Context
) : ListAdapter<CityInlinedDomainModel, RecyclerView.ViewHolder>(CitiesListDiffCallback()) {

    override fun getItemViewType(position: Int) = R.layout.item_city

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CityItemViewHolder(
            context,
            ItemCityBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CityItemViewHolder).bind(getItem(position))
    }

    private class CitiesListDiffCallback :
        DiffUtil.ItemCallback<CityInlinedDomainModel>() {

        override fun areItemsTheSame(
            oldItem: CityInlinedDomainModel,
            newItem: CityInlinedDomainModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: CityInlinedDomainModel,
            newItem: CityInlinedDomainModel
        ): Boolean {
            return oldItem.id == newItem.id
                    && oldItem.name == newItem.name
                    && oldItem.dt == newItem.dt
                    && oldItem.dayName == newItem.dayName
                    && oldItem.dayNumber == newItem.dayNumber
                    && oldItem.temp == newItem.temp
                    && oldItem.temp_min == newItem.temp_min
                    && oldItem.temp_max == newItem.temp_max
                    && oldItem.icon == newItem.icon
                    && oldItem.description == newItem.description
        }
    }
}