package ru.nolimits.alexander.blockermaskcaller.screens.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.data.Mask
import ru.nolimits.alexander.blockermaskcaller.databinding.FragmentListItemBinding


class MasksAdapter(
    var masksList: List<Mask> = listOf(),
    val callback: Callback
) : RecyclerView.Adapter<MasksAdapter.MaskHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaskHolder {

        val binding = FragmentListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MaskHolder(binding)
    }

    override fun getItemCount(): Int = masksList.size

    override fun onBindViewHolder(viewHolder: MaskHolder, position: Int) {
        viewHolder.bind(masksList[position])
    }

    fun refreshPhoneMasks(masks: List<Mask>) {
        this.masksList = masks
        notifyDataSetChanged()
    }

    inner class MaskHolder(binding: FragmentListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

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