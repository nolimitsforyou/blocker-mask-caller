package ru.nolimits.alexander.blockermaskcaller.view.activities

import android.app.role.RoleManager
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.view.models.ListMasksViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val REQUEST_ID = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        //TODO возможно в активити определять есть ли маски и запускать соотв. фрагмент
        val model: ListMasksViewModel by viewModels()

        val count = model.getCountMasks()

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