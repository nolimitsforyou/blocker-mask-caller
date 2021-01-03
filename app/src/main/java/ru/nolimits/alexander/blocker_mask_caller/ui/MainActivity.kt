package ru.nolimits.alexander.blocker_mask_caller.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.add
import androidx.fragment.app.commit
import ru.nolimits.alexander.blocker_mask_caller.R
import ru.nolimits.alexander.blocker_mask_caller.ui.fragments.MasksListFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<MasksListFragment>(R.id.fragment_container_view)
            }
        }
    }
}