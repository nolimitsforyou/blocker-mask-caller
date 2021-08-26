package ru.nolimits.alexander.blockermaskcaller.view.activities

import android.app.role.RoleManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ru.nolimits.alexander.blockermaskcaller.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

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

    private fun requestRole() {
        val roleManager = getSystemService(ROLE_SERVICE) as RoleManager
        if (roleManager.isRoleAvailable(RoleManager.ROLE_CALL_SCREENING)) {
            val intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING)
            startActivityForResult(intent, REQUEST_ID)
        }
    }
}