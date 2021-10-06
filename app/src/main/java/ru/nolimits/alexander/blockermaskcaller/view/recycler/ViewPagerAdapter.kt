package ru.nolimits.alexander.blockermaskcaller.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.nolimits.alexander.blockermaskcaller.databinding.ItemEducationPageBinding

class ViewPagerAdapter(private val listEducationText: List<String>) :
    RecyclerView.Adapter<ViewPagerAdapter.PagerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerHolder {

        val binding = ItemEducationPageBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PagerHolder(binding)
    }

    override fun onBindViewHolder(holder: PagerHolder, position: Int) {
        with(holder) {
            binding.apply {
                educationText.text = listEducationText[position]
            }
        }
    }

    override fun getItemCount(): Int = listEducationText.size

    inner class PagerHolder(val binding: ItemEducationPageBinding) :
        RecyclerView.ViewHolder(binding.root)
}

