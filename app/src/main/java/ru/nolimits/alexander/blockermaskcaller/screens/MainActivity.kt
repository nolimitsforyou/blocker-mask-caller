package ru.nolimits.alexander.blockermaskcaller.screens

import android.app.role.RoleManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.data.Mask
import ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.item.ItemMaskFragment
import ru.nolimits.alexander.blockermaskcaller.shared.Communicator

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Communicator {

    private val REQUEST_ID = 1
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        requestRole()
    }

    override fun sendData(mask: Mask) {
        val fr = ItemMaskFragment.newInstance(mask)
        supportFragmentManager.commit {
            addToBackStack(null)
            replace(R.id.fragment_container_view, fr)
        }
    }

    private fun requestRole() {
        val roleManager = getSystemService(ROLE_SERVICE) as RoleManager
        if (roleManager.isRoleAvailable(RoleManager.ROLE_CALL_SCREENING)) {
            val intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING)
            startActivityForResult(intent, REQUEST_ID)
        }
    }
}