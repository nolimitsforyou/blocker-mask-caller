package ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.list

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list_masks.*
import ru.nolimits.alexander.blockermaskcaller.PhoneMasksApplication
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.database.Mask
import ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.item.ItemMaskFragment
import ru.nolimits.alexander.blockermaskcaller.screens.recyclerview.MasksAdapter
import ru.nolimits.alexander.blockermaskcaller.shared.Communicator

class MasksListFragment : Fragment() {

    private lateinit var viewModel: ListMasksViewModel
    private lateinit var viewModelFactory: ListMasksViewModelFactory
    private lateinit var fm: FragmentManager

    private lateinit var communicator: Communicator


    companion object {
        fun newInstance(): MasksListFragment = MasksListFragment()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list_masks, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_new_mask -> {
                fm.commit {
                    addToBackStack(null)
                    replace(R.id.fragment_container_view, ItemMaskFragment.newInstance())
                }
                true
            }
            R.id.delete_all_masks -> {
                viewModel.deleteAll()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        fm = activity?.supportFragmentManager!!
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        communicator = activity as Communicator

        viewModelFactory =
            ListMasksViewModelFactory((activity?.applicationContext as PhoneMasksApplication).repository)
        Log.i("MasksListFragment", "Called ListMasksViewModel.get")

        viewModel = ViewModelProvider(this, viewModelFactory).get(ListMasksViewModel::class.java)

        return inflater.inflate(R.layout.fragment_list_masks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterMasks = MasksAdapter(callback = object : MasksAdapter.Callback {
            override fun onItemClicked(item: Mask) {
                communicator.sendData(item)
            }
        })

        masks_recyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = adapterMasks
        }

        viewModel.allMasks.observe(viewLifecycleOwner, {
            it?.let {
                adapterMasks.refreshPhoneMasks(it)
            }
        })
        setRecyclerViewItemTouchListener()
    }

    private fun setRecyclerViewItemTouchListener() {

        val itemTouchCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                viewHolder1: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                val maskId = viewModel.allMasks.value?.get(position)?.id
                viewModel.delete(maskId!!)
                masks_recyclerview.adapter!!.notifyItemRemoved(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(masks_recyclerview)
    }
}