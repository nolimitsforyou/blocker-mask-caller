package ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.item

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_new_mask.*
import ru.nolimits.alexander.blockermaskcaller.PhoneMasksApplication
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.database.Mask
import ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.list.MasksListFragment

class ItemMaskFragment : Fragment() {

    private lateinit var viewModel: ItemMaskViewModel
    private lateinit var viewModelFactory: ItemMaskViewModelFactory
    private lateinit var fm: FragmentManager
    private lateinit var mask: Mask

    private var idMask: Int? = null
    private var titleMask: String? = null
    private var numericMask: String? = null

    companion object {

        const val MASK_ID = "mask_id"
        const val MASK_TITLE = "mask_title"
        const val MASK_NUMERIC = "mask_numeric"

        fun newInstance(mask: Mask? = null): ItemMaskFragment {
            val bundle = Bundle()
            mask?.let {
                bundle.putInt(MASK_ID, mask.id)
                bundle.putString(MASK_TITLE, mask.title)
                bundle.putString(MASK_NUMERIC, mask.numeric)
            }
            val fr = ItemMaskFragment()
            fr.arguments = bundle
            return fr
        }
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

        idMask = arguments?.getInt(MASK_ID)
        titleMask = arguments?.getString(MASK_TITLE)
        numericMask = arguments?.getString(MASK_NUMERIC)

        idMask?.let {
//            mask = viewModel.getMaskById(it)
            name_mask.setText(titleMask)
            phone_mask.setText(numericMask)
        }


        return inflater.inflate(R.layout.fragment_new_mask, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_add.setOnClickListener {

            if (!TextUtils.isEmpty(phone_mask.text)) {

                mask =
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