package ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.item

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_new_mask.*
import ru.nolimits.alexander.blockermaskcaller.PhoneMasksApplication
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.database.Mask
import ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.list.MasksListFragment
import ru.nolimits.alexander.blockermaskcaller.shared.SharedViewModel

class ItemMaskFragment : Fragment() {

    private lateinit var viewModel: ItemMaskViewModel
    private lateinit var viewModelFactory: ItemMaskViewModelFactory
    private lateinit var fm: FragmentManager

    private val sharedViewModel: SharedViewModel by activityViewModels()

    companion object {
        fun newInstance(): ItemMaskFragment = ItemMaskFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        fm = activity?.supportFragmentManager!!
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModelFactory =
            ItemMaskViewModelFactory((activity?.applicationContext as PhoneMasksApplication).repository)

        Log.i("MasksListFragment", "Called ListMasksViewModel.get")
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemMaskViewModel::class.java)

        return inflater.inflate(R.layout.fragment_new_mask, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.phoneMask.observe(viewLifecycleOwner, { mask ->
            name_mask.setText(mask.title)
            phone_mask.setText(mask.numeric)
        })

        button_add.setOnClickListener {

            if (!TextUtils.isEmpty(phone_mask.text)) {

                val mask =
                    Mask(numeric = phone_mask.text.toString(), title = name_mask.text.toString())
                viewModel.insert(mask)

                fm.commit {
                    replace(R.id.fragment_container_view, MasksListFragment.newInstance())
                }

            } else {
                //TODO сделать если не заполнено поле
                AlertDialog.BUTTON_POSITIVE
            }
        }
    }
}