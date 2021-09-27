package ru.nolimits.alexander.blockermaskcaller.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nolimits.alexander.blockermaskcaller.databinding.ItemEducationPageBinding

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.PagerHolder>() {

    private val listEducationText = listOf(
        "один один",
        "два два",
        "три три"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerHolder {

        val binding = ItemEducationPageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PagerHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerHolder, position: Int) = holder.itemView.run {
        holder.binding.educationText.text = listEducationText[position]
    }

    override fun getItemCount(): Int = listEducationText.size

    inner class PagerHolder(val binding: ItemEducationPageBinding) :
        RecyclerView.ViewHolder(binding.root)
}

