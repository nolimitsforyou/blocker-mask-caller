package ru.nolimits.alexander.blockermaskcaller.screens

import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.data.Mask
import ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.item.ItemMaskFragment
import ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.list.MasksListFragment
import ru.nolimits.alexander.blockermaskcaller.shared.Communicator
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : SingleFragmentActivity(), Communicator {

    @Inject lateinit var masksListFragment: MasksListFragment

    override fun createFragment(): Fragment {
        return masksListFragment
    }

    override fun sendData(mask: Mask) {
        val fr = ItemMaskFragment.newInstance(mask)
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.fragment_container_view, fr)
        }
    }
}