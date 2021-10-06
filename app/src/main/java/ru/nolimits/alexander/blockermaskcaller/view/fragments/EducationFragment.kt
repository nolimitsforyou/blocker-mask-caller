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
    private val listEducationText = listOf(
        """У Вас пока нет ни одного префикса телефонного номера для блокирования вызовов.
            | Добавьте первую "маску" для блокировки вызовов по совпадению первых 7
            | цифр телефонного номера""".trimMargin(),
        """Номер телефона, начало которого будет соответсвовать первым 7ми цифрам маски,
            | будет заблокирован. Пример: Вы добавили "маску" +7 999 888. Вам звонит абонент с номером
            | +7 999 888 77 66 - Такой вызов будет заблокирован""".trimMargin()
    )

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

        bindingViewPager.apply {
            viewPagerEducation.adapter = ViewPagerAdapter(listEducationText)
            buttonNext.setOnClickListener {
                if (viewPagerEducation.currentItem == listEducationText.lastIndex) {
                    navController.navigate(R.id.itemMaskFragment)
                } else {
                    viewPagerEducation.currentItem +=1
                }
            }
        }

        bindingViewPager.viewPagerEducation.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    if (position < listEducationText.lastIndex) {
                        bindingViewPager.buttonNext.setText(R.string.next_education)
                    } else if (position == listEducationText.lastIndex) {
                        bindingViewPager.buttonNext.setText(R.string.create_new)
                    }
                }
            }
        )
    }
}