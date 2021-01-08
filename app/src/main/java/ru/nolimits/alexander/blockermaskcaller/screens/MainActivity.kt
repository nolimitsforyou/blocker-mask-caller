package ru.nolimits.alexander.blockermaskcaller.screens

import ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.list.MasksListFragment


class MainActivity : SingleFragmentActivity() {

    override fun createFragment() = MasksListFragment.newInstance()
}