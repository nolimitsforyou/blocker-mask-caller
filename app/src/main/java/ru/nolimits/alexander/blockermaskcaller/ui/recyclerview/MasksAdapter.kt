package ru.nolimits.alexander.blockermaskcaller.ui.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list_item.view.*
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.database.Mask

class MasksAdapter(private var masks: List<Mask> = ArrayList()) :
    RecyclerView.Adapter<MasksAdapter.MaskHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaskHolder {
        return MaskHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MaskHolder, position: Int) {
        val phoneMask: Mask = masks[position]
        holder.bind(phoneMask)
    }

    override fun getItemCount(): Int = masks.size

    fun refreshPhoneMasks(masks: List<Mask>) {
        this.masks = masks
        notifyDataSetChanged()
    }

    class MaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(mask: Mask) = with(itemView) {
            phone_mask_number.text = mask.numeric
        }
    }
}