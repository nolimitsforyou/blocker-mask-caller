package ru.nolimits.alexander.blockermaskcaller.screens.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.database.Mask


class MasksAdapter(
    var masks: List<Mask> = listOf(),
    val callback: Callback
) : RecyclerView.Adapter<MasksAdapter.MaskHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaskHolder {
        val itemView =
            LayoutInflater.from(parent?.context).inflate(R.layout.fragment_list_item, parent, false)
        return MaskHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: MaskHolder, position: Int) {
        viewHolder.bind(masks[position])
    }

    override fun getItemCount(): Int = masks.size

    fun refreshPhoneMasks(masks: List<Mask>) {
        this.masks = masks
        notifyDataSetChanged()
    }

    inner class MaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val maskNameTextView: TextView = itemView.findViewById(R.id.phone_mask_name)
        private val maskNumberTextView: TextView = itemView.findViewById(R.id.phone_mask_number)

        fun bind(item: Mask) {
            maskNameTextView.text = item.title
            maskNumberTextView.text = item.numeric
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    callback.onItemClicked(masks[adapterPosition])
                }
            }
        }
    }

    interface Callback {
        fun onItemClicked(item: Mask)
    }
}