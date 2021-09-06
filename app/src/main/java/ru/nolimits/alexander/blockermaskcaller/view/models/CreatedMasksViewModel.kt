package ru.nolimits.alexander.blockermaskcaller.view.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.nolimits.alexander.blockermaskcaller.data.Mask
import ru.nolimits.alexander.blockermaskcaller.data.MasksRepository
import javax.inject.Inject

@HiltViewModel
class CreatedMasksViewModel @Inject constructor(private val repository: MasksRepository) :
    ViewModel() {

    val allMasks: LiveData<List<Mask>> = repository.allMasks.asLiveData()

}