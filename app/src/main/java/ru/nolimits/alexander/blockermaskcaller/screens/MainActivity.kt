package ru.nolimits.alexander.blockermaskcaller.screens

import androidx.fragment.app.Fragment
import ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.item.ItemMaskFragment
import ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.list.MasksListFragment


class MainActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment {
        return if (countMasks > 0) {
            MasksListFragment.newInstance()
        } else {
            ItemMaskFragment.newInstance()
        }
    }
}