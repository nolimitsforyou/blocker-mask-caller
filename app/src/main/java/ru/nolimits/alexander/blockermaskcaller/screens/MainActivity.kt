package ru.nolimits.alexander.blockermaskcaller.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.database.Mask
import ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.item.ItemMaskFragment
import ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.list.MasksListFragment
import ru.nolimits.alexander.blockermaskcaller.shared.Communicator


class MainActivity : SingleFragmentActivity(), Communicator {

    override fun createFragment(): Fragment {
        return MasksListFragment.newInstance()
    }

    override fun sendData(mask: Mask) {
        val fr = ItemMaskFragment.newInstance(mask.id)
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.fragment_container_view, fr)
        }
    }
}