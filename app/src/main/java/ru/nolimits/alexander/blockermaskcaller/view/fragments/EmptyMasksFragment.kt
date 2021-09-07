package ru.nolimits.alexander.blockermaskcaller.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import dagger.hilt.android.AndroidEntryPoint
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.databinding.FragmentEmptyMasksBinding

@AndroidEntryPoint
class EmptyMasksFragment : Fragment() {

    private var _binding: FragmentEmptyMasksBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

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
}