package ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_masks.*
import ru.nolimits.alexander.blockermaskcaller.PhoneMasksApplication
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.screens.recyclerview.MasksAdapter

class MasksListFragment : Fragment() {

    private lateinit var viewModel: ListMasksViewModel
    private lateinit var viewModelFactory: ListMasksViewModelFactory

    companion object {
        fun newInstance(): MasksListFragment = MasksListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val app = activity?.applicationContext
        viewModelFactory = ListMasksViewModelFactory((app as PhoneMasksApplication).repository)

        Log.i("MasksListFragment", "Called ListMasksViewModel.get")
        viewModel = ViewModelProvider(this, viewModelFactory).get(ListMasksViewModel::class.java)
        return inflater.inflate(R.layout.fragment_list_masks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterMasks = MasksAdapter()

        masks_recyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = adapterMasks
        }

        viewModel.allMasks.observe(viewLifecycleOwner, {
            it?.let {
                adapterMasks.refreshPhoneMasks(it)
            }
        })
    }
}