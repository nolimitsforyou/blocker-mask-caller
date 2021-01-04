package ru.nolimits.alexander.blocker_mask_caller.ui

import ru.nolimits.alexander.blocker_mask_caller.ui.fragments.MasksListFragment


class MainActivity : SingleFragmentActivity() {

    override fun createFragment() = MasksListFragment.newInstance()
}