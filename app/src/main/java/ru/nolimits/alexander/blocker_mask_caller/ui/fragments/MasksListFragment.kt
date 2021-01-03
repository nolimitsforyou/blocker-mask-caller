package ru.nolimits.alexander.blocker_mask_caller.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ru.nolimits.alexander.blocker_mask_caller.R
import kotlinx.android.synthetic.main.fragment_list_masks.*

class MasksListFragment : Fragment() {

    //TODO for testing
    private val phoneNumbers = listOf(
        PhoneMask("+7 999 99 99", "firstName"),
        PhoneMask("+7 888 88 88", "secondName")
    )

    companion object {
        fun newInstance() = MasksListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_masks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        masks_recyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = MasksListAdapter(phoneNumbers)
        }
    }

}