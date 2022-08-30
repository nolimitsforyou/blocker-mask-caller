package ru.nolimits.alexander.blockermaskcaller.view.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nolimits.alexander.blockermaskcaller.data.Mask
import ru.nolimits.alexander.blockermaskcaller.databinding.FragmentListItemBinding


class MasksAdapter(
    var masksList: List<Mask> = listOf(),
    var selectedList: List<Mask>,
    val callback: Callback
) : RecyclerView.Adapter<MasksAdapter.MaskHolder>() {

    private var showCheckbox: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaskHolder {

        val binding = FragmentListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MaskHolder(binding)
    }

    override fun onBindViewHolder(holder: MaskHolder, position: Int) {

        with(holder) {

            if (showCheckbox) {
                binding.checkBox.visibility = View.VISIBLE
            }

            with(masksList[position]) {
                binding.phoneMaskName.text = this.title
                binding.phoneMaskNumber.text = this.numeric
                binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                    callback.checkBoxClicked(masksList[position], isChecked)
                }
                //проверяем отмечен ли элемент чекбоксом
                binding.checkBox.isChecked = selectedList.contains(masksList[position])
            }

            itemView.setOnClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    callback.onItemClicked(masksList[bindingAdapterPosition])
                }
            }

            itemView.setOnLongClickListener {
                binding.checkBox.apply {
                    visibility = View.VISIBLE
                    isChecked = true
                    showCheckbox = true
                }
                notifyDataSetChanged()
                true
            }
        }
    }



    @SuppressLint("NotifyDataSetChanged")
    fun refreshPhoneMasks(masks: List<Mask>) {
        this.masksList = masks
        notifyDataSetChanged() //TODO переделать на notifyItemChanged() ?
    }

    override fun getItemCount(): Int = masksList.size

    inner class MaskHolder(val binding: FragmentListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    interface Callback {
        fun onItemClicked(item: Mask)
        fun checkBoxClicked(item: Mask, isChecked: Boolean)
    }
}