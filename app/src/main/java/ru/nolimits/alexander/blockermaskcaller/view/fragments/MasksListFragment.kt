package ru.nolimits.alexander.blockermaskcaller.view.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.data.Mask
import ru.nolimits.alexander.blockermaskcaller.databinding.FragmentListMasksBinding
import ru.nolimits.alexander.blockermaskcaller.data.MasksRepository
import ru.nolimits.alexander.blockermaskcaller.view.recycler.MasksAdapter
import ru.nolimits.alexander.blockermaskcaller.view.models.ListMasksViewModel
import ru.nolimits.alexander.blockermaskcaller.view.models.ListMasksViewModelFactory
import javax.inject.Inject

@AndroidEntryPoint
class MasksListFragment @Inject constructor() : Fragment() {

    @Inject
    lateinit var repository: MasksRepository
    private val readPhoneStatePermission = Manifest.permission.READ_PHONE_STATE
    private val requestCodeReadPhoneState = 1
    private var _bindingRecyclerView: FragmentListMasksBinding? = null
    private val bindingRecyclerView get() = _bindingRecyclerView!!
    private lateinit var viewModel: ListMasksViewModel
    private lateinit var viewModelFactory: ListMasksViewModelFactory

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_list_masks, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_new_mask -> {
                findNavController().navigate(R.id.itemMaskFragment)
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
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModelFactory =
            ListMasksViewModelFactory(repository)
        Log.i("MasksListFragment", "Called ListMasksViewModel.get")

        viewModel = ViewModelProvider(this, viewModelFactory).get(ListMasksViewModel::class.java)
        _bindingRecyclerView = FragmentListMasksBinding.inflate(inflater, container, false)

        return bindingRecyclerView.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterMasks = MasksAdapter(callback = object : MasksAdapter.Callback {
            override fun onItemClicked(item: Mask) {
                findNavController().navigate(
                    MasksListFragmentDirections
                        .actionMasksListFragmentToItemMaskFragment(item)
                )
            }
        })

        bindingRecyclerView.masksRecyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = adapterMasks
        }

        viewModel.allMasks.observe(viewLifecycleOwner, {
            it?.let {
                adapterMasks.refreshPhoneMasks(it)
            }
        })
        setRecyclerViewItemTouchListener()
        checkPermission()
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
                bindingRecyclerView.masksRecyclerview.adapter!!.notifyItemRemoved(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(bindingRecyclerView.masksRecyclerview)
    }

    private fun checkPermission() {

        when {
            checkSelfPermission(
                activity?.applicationContext!!,
                readPhoneStatePermission
            ) == PackageManager.PERMISSION_GRANTED -> {
                //ничего не делаем
            }
            shouldShowRequestPermissionRationale(readPhoneStatePermission) -> {
                //показываем разъясняющий диалог
                showCustomAlertDialog()
            }
            else -> {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(readPhoneStatePermission),
                    requestCodeReadPhoneState
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            requestCodeReadPhoneState -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)
                ) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                } else {
                    showCustomAlertDialog()
                }
                return
            }
            else -> {
                // Ignore all other requests.
            }
        }
    }

    private fun showCustomAlertDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog
            .setTitle(R.string.alert_dialog_title)
            .setMessage(R.string.alert_dialog_message)
            .setPositiveButton(
                R.string.button_ok
            ) { dialog, _ ->
                dialog.cancel()
            }
        alertDialog.show()
    }
}