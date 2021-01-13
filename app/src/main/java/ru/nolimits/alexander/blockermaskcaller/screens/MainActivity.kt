package ru.nolimits.alexander.blockermaskcaller.screens

import androidx.fragment.app.Fragment
import ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.list.MasksListFragment


class MainActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment {
        return MasksListFragment.newInstance()
    }
}