package ru.nolimits.alexander.blocker_mask_caller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MasksListFragment : Fragment() {

    private val phoneNumbers = listOf("+7 999 99 99", "+7 888 88 88")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        return super.onCreateView(inflater, container, savedInstanceState)
        val v = inflater.inflate(R.layout.fragment_list_masks, container, false)
        val recyclerView: RecyclerView = v.findViewById(R.id.masks_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(context)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        masks_recyclerview
    }
}