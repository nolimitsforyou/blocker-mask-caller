package ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.item

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_new_mask.*
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.database.Mask
import ru.nolimits.alexander.blockermaskcaller.database.MasksApplication


class ItemMaskFragment : Fragment() {

    private val application = requireActivity().application

    private val newMaskViewModel: NewMaskViewModel by viewModels {
        NewMaskViewModelFactory((application as MasksApplication).repository)
    }

    companion object {
        fun newInstance(): ItemMaskFragment = ItemMaskFragment()
        const val EXTRA_REPLY = "ru.nolimits.alexander.blockermaskcaller.REPLY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_mask, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_add.setOnClickListener {
            if (!TextUtils.isEmpty(phone_mask.text)) {
                val mask = Mask(numeric = phone_mask.text.toString(), title = name_mask.text.toString())
                newMaskViewModel.insert(mask)
                //TODO писать в БД
            } else {
                //TODO сделать если не заполнено поле
                AlertDialog.BUTTON_POSITIVE
            }
        }
    }
}