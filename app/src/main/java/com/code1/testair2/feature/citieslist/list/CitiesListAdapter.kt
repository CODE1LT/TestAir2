package lt.code1.testair.features.citieslist.list

import android.content.Context
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import lt.code1.testair.R
import lt.code1.testair.base.DataBindingAdapter
import lt.code1.testair.databinding.ItemCityBinding
import com.code1.testair2.feature.citieslist.domain.model.CityInlinedDomainModel
import timber.log.Timber

class CitiesListAdapter (
    private val context: Context
) : DataBindingAdapter<CityInlinedDomainModel, RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int) = R.layout.item_city

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Timber.d("onBindViewHolder(), position=$position")
        (holder as CityItemViewHolder).bind(data[position])
    }

    override fun getDataBindingViewHolder(
        binding: ViewDataBinding,
        viewType: Int
    ): RecyclerView.ViewHolder {
        Timber.d("getDataBindingViewHolder()")
        return CityItemViewHolder(context, binding as ItemCityBinding)
    }
}