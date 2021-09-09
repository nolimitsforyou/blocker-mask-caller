package ru.nolimits.alexander.blockermaskcaller.view.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.nolimits.alexander.blockermaskcaller.R

class EducationAdapter(private val educationTextsList: List<String>) :
    RecyclerView.Adapter<EducationAdapter.EducationViewHolder>() {

    class EducationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var educationTextView: TextView? = null

        init {
            educationTextView = itemView.findViewById(R.id.education_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_education_item, parent, false)

        return EducationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        with(holder) {
            with(educationTextsList[position]) {
                educationTextView?.text = educationTextsList[position]
            }
        }
    }

    override fun getItemCount(): Int = educationTextsList.size
}