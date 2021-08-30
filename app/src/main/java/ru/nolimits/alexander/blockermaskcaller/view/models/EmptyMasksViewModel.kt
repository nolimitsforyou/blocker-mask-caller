package ru.nolimits.alexander.blockermaskcaller.view.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import ru.nolimits.alexander.blockermaskcaller.data.Mask
import ru.nolimits.alexander.blockermaskcaller.data.MasksRepository

class EmptyMasksViewModel(private val repository: MasksRepository) : ViewModel() {

    val allMasks: LiveData<List<Mask>> = repository.allMasks.asLiveData()

}