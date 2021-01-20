package ru.nolimits.alexander.blockermaskcaller.shared

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.nolimits.alexander.blockermaskcaller.database.Mask

class SharedViewModel : ViewModel() {

    val phoneMask = MutableLiveData<Mask>()

    fun setMask(mask: Mask) {
        phoneMask.value = mask
    }
}