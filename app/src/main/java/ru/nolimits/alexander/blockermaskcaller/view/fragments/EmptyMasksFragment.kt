package ru.nolimits.alexander.blockermaskcaller.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.databinding.FragmentEmptyMasksBinding

@AndroidEntryPoint
class EmptyMasksFragment : Fragment() {

//    companion object {
//        const val USER_PRESS_CANCELED: String = "USER_PRESS_CANCELED"
//    }
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        savedStateHandle = findNavController().previousBackStackEntry!!.savedStateHandle
//        savedStateHandle.set(USER_PRESS_CANCELED, false)

        binding.buttonCreateNew.setOnClickListener {
            navController.navigate(R.id.itemMaskFragment)
        }

        binding.buttonNotNow.setOnClickListener {
            //TODO убрать, делаем view pager ?
//            savedStateHandle.set(USER_PRESS_CANCELED, true)
//            navController.navigate(R.id.masksListFragment)
        }
    }
}