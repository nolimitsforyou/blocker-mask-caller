package ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.item

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_new_mask.*
import kotlinx.coroutines.launch
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.data.Mask
import ru.nolimits.alexander.blockermaskcaller.repository.MasksRepository
import javax.inject.Inject

@AndroidEntryPoint
class ItemMaskFragment : Fragment() {

    @Inject
    lateinit var repository: MasksRepository
    private lateinit var phoneNumberAlertText: String
    private lateinit var viewModel: ItemMaskViewModel
    private lateinit var viewModelFactory: ItemMaskViewModelFactory
    private lateinit var navController: NavController
    private var maskItem: Mask? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        navController =
            Navigation.findNavController(requireActivity(), R.id.fragment_container_view)
        phoneNumberAlertText = getString(R.string.alert_phone_number)
        //TODO может быть null - надо учесть
        val bundle = arguments
        if (bundle == null) {
            Log.e("MaskCall", "ItemMaskFragment не получил информацию о маске")
            return
        }
        val args = bundle.let { ItemMaskFragmentArgs.fromBundle(it) }
        maskItem = args.maskItem
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModelFactory = ItemMaskViewModelFactory(repository)

        Log.i("MasksListFragment", "Called ListMasksViewModel.get")
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemMaskViewModel::class.java)

        maskItem?.let {
            lifecycleScope.launch {
                name_mask.setText(it.title)
                phone_mask.setText(it.numeric)
                phone_mask.addTextChangedListener(object : TextWatcher {

                    override fun beforeTextChanged(
                        text: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {

                    }

                    override fun onTextChanged(
                        text: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                    }

                    override fun afterTextChanged(chars: Editable?) {
                        if (chars?.length!! < 7 || chars.isNullOrEmpty()) {
                            phone_mask.error = phoneNumberAlertText
                        } else {
                            //TODO заменять первую цифру на 7 ?
                        }
                    }
                })
            }
        }

        return inflater.inflate(R.layout.fragment_new_mask, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_add.setOnClickListener {

            if (maskItem != null && phone_mask.text.length == 7) {
                viewModel.update(
                    Mask(
                        id = maskItem!!.id,
                        numeric = phone_mask.text.toString(),
                        title = name_mask.text.toString()
                    )
                )
                navController.popBackStack()
            } else if (phone_mask.text.length == 7) {
                viewModel.insert(
                    Mask(
                        numeric = phone_mask.text.toString(),
                        title = name_mask.text.toString()
                    )
                )
                navController.popBackStack()
            } else {
                phone_mask.error = phoneNumberAlertText
            }
        }

        button_delete.setOnClickListener {
            if (maskItem != null) {
                viewModel.delete(maskItem!!.id)
            }
            navController.popBackStack()
        }
    }
}