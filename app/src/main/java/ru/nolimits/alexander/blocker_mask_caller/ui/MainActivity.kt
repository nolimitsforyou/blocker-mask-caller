package ru.nolimits.alexander.blocker_mask_caller.ui

import androidx.fragment.app.Fragment
import ru.nolimits.alexander.blocker_mask_caller.ui.fragments.MasksListFragment


class MainActivity : SingleFragmentActivity() {

    override fun createFragment() = MasksListFragment.newInstance()
}