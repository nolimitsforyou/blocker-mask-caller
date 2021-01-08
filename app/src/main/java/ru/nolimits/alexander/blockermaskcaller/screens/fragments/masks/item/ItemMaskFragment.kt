package ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.item

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_new_mask.*
import ru.nolimits.alexander.blockermaskcaller.PhoneMasksApplication
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.database.Mask

class ItemMaskFragment : Fragment() {

    private lateinit var viewModel: ItemMaskViewModel
    private lateinit var viewModelFactory: ItemMaskViewModelFactory

    companion object {
        fun newInstance(): ItemMaskFragment = ItemMaskFragment()
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
        button_add.setOnClickListener {
            if (!TextUtils.isEmpty(phone_mask.text)) {
                val mask = Mask(numeric = phone_mask.text.toString(), title = name_mask.text.toString())
//                newMaskViewModel.insert(mask)
                //TODO писать в БД
            } else {
                //TODO сделать если не заполнено поле
                AlertDialog.BUTTON_POSITIVE
            }
        }
    }
}