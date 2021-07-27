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
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.data.Mask
import ru.nolimits.alexander.blockermaskcaller.databinding.FragmentNewMaskBinding
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
    private var _binding: FragmentNewMaskBinding? = null
    private val binding get() = _binding!!
    private var maskItem: Mask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModelFactory = ItemMaskViewModelFactory(repository)

        Log.i("MasksListFragment", "Called ListMasksViewModel.get")
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemMaskViewModel::class.java)

        _binding = FragmentNewMaskBinding.inflate(inflater, container, false)
        val view = binding.root

        maskItem?.let {
            binding.nameMask.setText(it.title)
            binding.phoneMask.setText(it.numeric)
            binding.phoneMask.addTextChangedListener(object : TextWatcher {

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
                        binding.phoneMask.error = phoneNumberAlertText
                    } else {
                        //TODO заменять первую цифру на 7 ?
                    }
                }
            })
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAdd.setOnClickListener {

            if (maskItem != null && binding.phoneMask.text.length == 7) {
                viewModel.update(
                    Mask(
                        id = maskItem!!.id,
                        numeric = binding.phoneMask.text.toString(),
                        title = binding.nameMask.text.toString()
                    )
                )
                navController.popBackStack()
            } else if (binding.phoneMask.text.length == 7) {
                viewModel.insert(
                    Mask(
                        numeric = binding.phoneMask.text.toString(),
                        title = binding.nameMask.text.toString()
                    )
                )
                navController.popBackStack()
            } else {
                binding.phoneMask.error = phoneNumberAlertText
            }
        }

        binding.buttonDelete.setOnClickListener {
            if (maskItem != null) {
                viewModel.delete(maskItem!!.id)
            }
            navController.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}