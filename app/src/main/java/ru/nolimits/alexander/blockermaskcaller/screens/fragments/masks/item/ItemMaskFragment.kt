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
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.fragment_new_mask.*
import kotlinx.coroutines.launch
import ru.nolimits.alexander.blockermaskcaller.PhoneMasksApplication
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.database.Mask
import ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.list.MasksListFragment

class ItemMaskFragment : Fragment() {

    private lateinit var viewModel: ItemMaskViewModel
    private lateinit var viewModelFactory: ItemMaskViewModelFactory
    private lateinit var fm: FragmentManager
    private var idMask: Int? = null

    companion object {

        const val MASK_ID = "mask_id"

        fun newInstance(mask: Mask? = null): ItemMaskFragment {
            val fr = ItemMaskFragment()
            if (mask != null) {
                val bundle = Bundle()
                bundle.putInt(MASK_ID, mask.id)
                fr.arguments = bundle
            }
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

        idMask?.let {
            lifecycleScope.launch {
                val mask = viewModel.getMaskById(it)
                name_mask.setText(mask.title)
                phone_mask.setText(mask.numeric)
            }
        }

        return inflater.inflate(R.layout.fragment_new_mask, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_add.setOnClickListener {

            if (!TextUtils.isEmpty(phone_mask.text)) {

                if (idMask != null) {
                    viewModel.update(
                        Mask(
                            id = idMask!!,
                            numeric = phone_mask.text.toString(),
                            title = name_mask.text.toString()
                        )
                    )
                } else {
                    viewModel.insert(
                        Mask(
                            numeric = phone_mask.text.toString(),
                            title = name_mask.text.toString()
                        )
                    )
                }

                fm.commit {
                    replace(R.id.fragment_container_view, MasksListFragment.newInstance())
                }

            } else {
                //TODO показывать предупреждение, о том что обязательно должен быть введён номер
                phone_mask.error = "необходимо ввести префикс номера"
            }
        }

        button_delete.setOnClickListener {
            if (idMask != null) {
                viewModel.delete(idMask!!)
            }
            fm.commit {
                replace(R.id.fragment_container_view, MasksListFragment.newInstance())
            }
        }
    }
}