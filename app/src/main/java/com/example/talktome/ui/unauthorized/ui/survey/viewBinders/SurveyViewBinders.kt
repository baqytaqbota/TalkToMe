package com.example.talktome.ui.unauthorized.ui.survey.viewBinders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewBinder
import com.drakeet.multitype.MultiTypeAdapter
import com.example.talktome.R
import com.example.talktome.ui.unauthorized.ui.survey.data.UIGender
import com.example.talktome.ui.unauthorized.ui.survey.data.UIServiceType
import com.example.talktome.utils.decorator.GridItemDecorator
import com.example.talktome.utils.decorator.VerticalItemDecorator
import kotlinx.android.synthetic.main.item_checkable_grid_box.view.*
import kotlinx.android.synthetic.main.item_survey_check_list.view.*
import kotlinx.android.synthetic.main.item_survey_checkable_vertical_box.view.*
import kotlinx.android.synthetic.main.item_survey_list_title.view.*

class ListTitleViewBinder : ItemViewBinder<String, ListTitleViewBinder.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, item: String) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup) =
        ViewHolder(inflater.inflate(R.layout.item_survey_list_title, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: String) = with(itemView) {
            surveyListTitle.text = item
        }
    }
}

class GenderCheckListViewBinder(private val listener: ((UIGender) -> Unit)) :
    ItemViewBinder<List<UIGender>, GenderCheckListViewBinder.ViewHolder>() {

    override fun onBindViewHolder(
        holder: GenderCheckListViewBinder.ViewHolder,
        item: List<UIGender>
    ) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) = ViewHolder(inflater.inflate(R.layout.item_survey_check_list, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val multiTypeAdapter = MultiTypeAdapter().apply {
            register(GenderCheckListItemViewBinder{
                listener.invoke(it)
            })
        }

        init {
            with(itemView.surveyCheckBoxRecycler) {
                adapter = multiTypeAdapter
                if (itemDecorationCount == 0)
                    addItemDecoration(GridItemDecorator(3, 16))
            }
        }

        fun bind(item: List<UIGender>) = with(itemView) {
            multiTypeAdapter.apply {
                items = item
                notifyDataSetChanged()
            }
        }
    }
}

class GenderCheckListItemViewBinder(private val listener: ((UIGender) -> Unit)) :
    ItemViewBinder<UIGender, GenderCheckListItemViewBinder.ViewHolder>() {

    var previousCheckedPosition = -1
    var mCheckedPosition = -1

    override fun onBindViewHolder(
        holder: GenderCheckListItemViewBinder.ViewHolder,
        item: UIGender
    ) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) = ViewHolder(inflater.inflate(R.layout.item_checkable_grid_box, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: UIGender) = with(itemView) {
            valueTextView.text = item.genderName

            val isChecked = layoutPosition == mCheckedPosition

            if (isChecked) {
                previousCheckedPosition = layoutPosition
                setCheckedView(true)
            } else {
                setCheckedView(false)
            }

            valueTextView.setOnClickListener {
                mCheckedPosition = if (isChecked) -1 else layoutPosition
                adapter.notifyItemChanged(previousCheckedPosition)
                adapter.notifyItemChanged(layoutPosition)
                listener.invoke(item)
            }

        }

        private fun setCheckedView(checked: Boolean) = with(itemView) {
            valueTextView.isChecked = checked
            when (checked) {
                true -> valueTextView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.textColorWithBackground
                    )
                )
                else -> valueTextView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.secondaryTextColor
                    )
                )
            }
        }

    }
}


class ServiceCheckListViewBinder(private val listener: ((List<UIServiceType>) -> Unit)) :
    ItemViewBinder<List<UIServiceType>, ServiceCheckListViewBinder.ViewHolder>() {

    override fun onBindViewHolder(
        holder: ServiceCheckListViewBinder.ViewHolder,
        item: List<UIServiceType>
    ) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) = ViewHolder(inflater.inflate(R.layout.item_survey_check_list, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val multiTypeAdapter = MultiTypeAdapter().apply {
            register(ServiceCheckListItemViewBinder{
                listener.invoke(it)
            })
        }

        init {
            with(itemView.surveyCheckBoxRecycler) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                adapter = multiTypeAdapter
                if (itemDecorationCount == 0)
                    addItemDecoration(VerticalItemDecorator(32, 16))
            }
        }


        fun bind(item: List<UIServiceType>) = with(itemView) {
            multiTypeAdapter.apply {
                items = item
                notifyDataSetChanged()
            }
        }
    }
}

class ServiceCheckListItemViewBinder(private val listener: ((List<UIServiceType>) -> Unit)) :
    ItemViewBinder<UIServiceType, ServiceCheckListItemViewBinder.ViewHolder>() {

    val selectedList = arrayListOf<UIServiceType>()

    override fun onBindViewHolder(
        holder: ServiceCheckListItemViewBinder.ViewHolder,
        item: UIServiceType
    ) {
        holder.bind(item)
    }

    override fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent: ViewGroup
    ) = ViewHolder(inflater.inflate(R.layout.item_survey_checkable_vertical_box, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: UIServiceType) = with(itemView) {
            serviceTextView.text = item.serviceType

            setCheckedView(item.isSelected)

            serviceTextView.setOnClickListener {
                item.isSelected = !item.isSelected
                if(item.isSelected){
                    selectedList.add(item)
                }else{
                    if(selectedList.contains(item))
                        selectedList.remove(item)
                }
                listener.invoke(selectedList)
                adapter.notifyItemChanged(layoutPosition)
            }

        }

        private fun setCheckedView(checked: Boolean) = with(itemView) {
            serviceTextView.isChecked = checked
            when (checked) {
                true -> serviceTextView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.textColorWithBackground
                    )
                )
                else -> serviceTextView.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.secondaryTextColor
                    )
                )
            }
        }

    }
}