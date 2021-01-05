package ru.nolimits.alexander.blockermaskcaller.data.view.models

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.nolimits.alexander.blockermaskcaller.database.Mask
import ru.nolimits.alexander.blockermaskcaller.repository.MasksRepository

class NewMaskViewModel(private val repository: MasksRepository) : ViewModel() {

    fun insert(mask: Mask) = viewModelScope.launch {
        repository.insert(mask)
    }
}

class NewMaskViewModelFactory(private val repository: MasksRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewMaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NewMaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}