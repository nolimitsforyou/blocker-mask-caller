package ru.nolimits.alexander.blockermaskcaller.ui.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_new_mask.*
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.data.view.models.NewMaskViewModel
import ru.nolimits.alexander.blockermaskcaller.data.view.models.NewMaskViewModelFactory
import ru.nolimits.alexander.blockermaskcaller.database.MasksApplication


class NewMaskFragment : Fragment() {

    private val application = requireActivity().application

    private val newMaskViewModel: NewMaskViewModel by viewModels {
        NewMaskViewModelFactory((application as MasksApplication).repository)
    }

    companion object {
        fun newInstance(): NewMaskFragment = NewMaskFragment()
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
            val replyIntent = Intent()
            if (!TextUtils.isEmpty(phone_mask.text)) {
                val mask = phone_mask.text.toString()
                //TODO писать в БД
            } else {
                //TODO сделать если не заполнено поле
                AlertDialog.BUTTON_POSITIVE
            }
        }
    }
}