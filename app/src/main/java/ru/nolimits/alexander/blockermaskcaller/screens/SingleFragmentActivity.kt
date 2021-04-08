package ru.nolimits.alexander.blockermaskcaller.screens

import android.app.role.RoleManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.nolimits.alexander.blockermaskcaller.R


abstract class SingleFragmentActivity : AppCompatActivity() {

    private val REQUEST_ID = 1

    private val layoutResId: Int
        @LayoutRes
        get() = R.layout.activity_fragment

    protected abstract fun createFragment(): Fragment

    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        val fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.fragment_container_view)
        // ensures fragments already created will not be created
        if (fragment == null) {
            fragment = createFragment()
            // create and commit a fragment transaction
            fm.beginTransaction()
                .add(R.id.fragment_container_view, fragment)
                .commit()
        }
        requestRole()
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    fun requestRole() {
        val roleManager = getSystemService(ROLE_SERVICE) as RoleManager
        val intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_CALL_SCREENING)
        startActivityForResult(intent, REQUEST_ID)
    }
}