package ru.nolimits.alexander.blockermaskcaller.screens

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.nolimits.alexander.blockermaskcaller.PhoneMasksApplication
import ru.nolimits.alexander.blockermaskcaller.R
import ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.list.ListMasksViewModel
import ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.list.ListMasksViewModelFactory

abstract class SingleFragmentActivity : AppCompatActivity() {

    private val layoutResId: Int
        @LayoutRes
        get() = R.layout.activity_fragment

    private lateinit var viewModel: ListMasksViewModel
    private lateinit var viewModelFactory: ListMasksViewModelFactory
    var countMasks: Int = 0

    protected abstract fun createFragment(): Fragment

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
        viewModelFactory =
            ListMasksViewModelFactory((application as PhoneMasksApplication).repository)
        Log.i("MasksListFragment", "Called ListMasksViewModel.get")
        viewModel = ViewModelProvider(this, viewModelFactory).get(ListMasksViewModel::class.java)
        countMasks = viewModel.allMasks.value?.size ?: 0
    }
}