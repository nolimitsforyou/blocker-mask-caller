package ru.nolimits.alexander.blockermaskcaller.ui

import ru.nolimits.alexander.blockermaskcaller.ui.fragments.MasksListFragment


class MainActivity : SingleFragmentActivity() {

    override fun createFragment() = MasksListFragment.newInstance()
}