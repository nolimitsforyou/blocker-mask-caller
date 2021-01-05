package ru.nolimits.alexander.blockermaskcaller.data.view.models

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.nolimits.alexander.blockermaskcaller.database.Mask
import ru.nolimits.alexander.blockermaskcaller.repository.MasksRepository

class MaskViewModel(private val repository: MasksRepository) : ViewModel() {

    val allMasks: LiveData<List<Mask>> = repository.allMasks.asLiveData()

    fun insert(mask: Mask) = viewModelScope.launch {
        repository.insert(mask)
    }
}

class MaskViewModelFactory(private val repository: MasksRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}