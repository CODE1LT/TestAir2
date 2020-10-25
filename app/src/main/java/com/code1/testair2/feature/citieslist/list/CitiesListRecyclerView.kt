package lt.code1.testair.features.citieslist.list

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import lt.code1.testair.R

class CitiesListRecyclerView(context: Context, attributeSet: AttributeSet?) :
    RecyclerView(context, attributeSet) {

    constructor(context: Context) : this(context, null)

    init {
        layoutManager = LinearLayoutManager(context)
        resources.getDimension(R.dimen.rv_default_padding)
            .toInt()
            .let { MarginItemDecoration(it) }
            .let { addItemDecoration(it) }
        this.adapter = CitiesListAdapter(context)
    }
}