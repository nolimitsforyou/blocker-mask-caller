package ru.nolimits.alexander.blockermaskcaller.screens.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.data.Mask


class MasksAdapter(
    var masksList: List<Mask> = listOf(),
    val callback: Callback
) : RecyclerView.Adapter<MasksAdapter.MaskHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaskHolder {
        val itemView =
            LayoutInflater.from(parent?.context).inflate(R.layout.fragment_list_item, parent, false)
        return MaskHolder(itemView)
    }

    override fun onBindViewHolder(viewHolder: MaskHolder, position: Int) {
        viewHolder.bind(masksList[position])
    }

    override fun getItemCount(): Int = masksList.size

    fun refreshPhoneMasks(masks: List<Mask>) {
        this.masksList = masks
        notifyDataSetChanged()
    }

    inner class MaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val maskNameTextView: TextView = itemView.findViewById(R.id.phone_mask_name)
        private val maskNumberTextView: TextView = itemView.findViewById(R.id.phone_mask_number)

        fun bind(item: Mask) {
            maskNameTextView.text = item.title
            maskNumberTextView.text = item.numeric
            itemView.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    callback.onItemClicked(masksList[bindingAdapterPosition])
                }
            }
        }
    }

    interface Callback {
        fun onItemClicked(item: Mask)
    }
}