package ru.nolimits.alexander.blockermaskcaller.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.databinding.EmptyMasksFragmentBinding
import ru.nolimits.alexander.blockermaskcaller.view.models.EmptyMasksViewModel

class EmptyMasksFragment : Fragment() {

    private var _binding: EmptyMasksFragmentBinding? = null
    private val binding get() = _binding!!
//    private lateinit var viewModel: EmptyMasksViewModel
//    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        navController =
//            Navigation.findNavController(requireActivity(), R.id.fragment_container_view)
//        viewModel = ViewModelProvider(this).get(EmptyMasksViewModel::class.java)
        _binding = EmptyMasksFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.buttonCreateNew.setOnClickListener {
            findNavController().navigate(R.id.itemMaskFragment)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}