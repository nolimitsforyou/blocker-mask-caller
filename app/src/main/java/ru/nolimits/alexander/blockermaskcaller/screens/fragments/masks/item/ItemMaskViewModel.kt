package ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.item

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.nolimits.alexander.blockermaskcaller.database.Mask
import ru.nolimits.alexander.blockermaskcaller.repository.MasksRepository

class NewMaskViewModel(private val repository: MasksRepository) : ViewModel() {

    init {
        Log.i("NewMaskViewModel", "NewMaskViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("NewMaskViewModel", "NewMaskViewModel destroyed!")
    }

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