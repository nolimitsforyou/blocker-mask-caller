package ru.nolimits.alexander.blockermaskcaller.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.databinding.FragmentEducationBinding
import ru.nolimits.alexander.blockermaskcaller.view.recycler.ViewPagerAdapter

@AndroidEntryPoint
class EducationFragment : Fragment() {

    private var _bindingViewPager: FragmentEducationBinding? = null
    private val bindingViewPager get() = _bindingViewPager!!
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

        _bindingViewPager = FragmentEducationBinding.inflate(inflater, container, false)

        return bindingViewPager.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingViewPager.viewPager2.adapter = ViewPagerAdapter(
            callBack = object : ViewPagerAdapter.CallBackViewPager {
                override fun onButtonNextClicked() {
                    navController.navigate(R.id.itemMaskFragment)
                }
            }
        )
        bindingViewPager.viewPager2.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    //TODO при смене экрана
                }
            }
        )
    }
}