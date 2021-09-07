package ru.nolimits.alexander.blockermaskcaller.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.databinding.FragmentEmptyMasksBinding
import ru.nolimits.alexander.blockermaskcaller.view.models.CreatedMasksViewModel

@AndroidEntryPoint
class EmptyMasksFragment : Fragment() {

    companion object {
        const val ADDED_NEW_MASK: String = "ADDED_NEW_MASK"
    }

    private val createdMasksViewModel: CreatedMasksViewModel by activityViewModels()
    private var _binding: FragmentEmptyMasksBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var savedStateHandle: SavedStateHandle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController =
            Navigation.findNavController(requireActivity(), R.id.fragment_container_view)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentEmptyMasksBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.buttonCreateNew.setOnClickListener {
            navController.navigate(R.id.itemMaskFragment)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
        savedStateHandle.set(ADDED_NEW_MASK, false)
    }
}