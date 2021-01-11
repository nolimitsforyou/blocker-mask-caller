package ru.nolimits.alexander.blockermaskcaller.screens

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.nolimits.alexander.blockermaskcaller.PhoneMasksApplication
import ru.nolimits.alexander.blockermaskcaller.R

abstract class SingleFragmentActivity : AppCompatActivity() {

    private val layoutResId: Int
        @LayoutRes
        get() = R.layout.activity_fragment

    var countMasks: Int = 0

    protected abstract fun createFragment(): Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
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
    }

    //TODO тут разобраться как получать правлиьно кол-во записей в БД (SharedPreferences ???)
    private fun setUp() {
        val app = application as PhoneMasksApplication
        val masksPhone = app.repository.allMasks
        lifecycleScope.launch {
            masksPhone.collect { countMasks = it.size }
        }
    }
}