package ru.nolimits.alexander.blocker_mask_caller.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.nolimits.alexander.blocker_mask_caller.R

class MasksListAdapter(private val list: List<PhoneMask>) : RecyclerView.Adapter<MaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaskViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MaskViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MaskViewHolder, position: Int) {
        val phoneMask: PhoneMask = list[position]
        holder.bind(phoneMask)
    }

    override fun getItemCount(): Int {
        return list.size
    }

}

class MaskViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.fragment_list_item, parent, false)) {
    private var phoneMask: TextView? = null

    init {
        phoneMask = itemView.findViewById(R.id.phone_mask_number)
    }

    fun bind(item: PhoneMask) {
        phoneMask?.text = item.mask
    }
}