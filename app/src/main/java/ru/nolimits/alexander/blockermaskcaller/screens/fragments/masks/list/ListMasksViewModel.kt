package ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.list

import android.util.Log
import androidx.lifecycle.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.nolimits.alexander.blockermaskcaller.database.Mask
import ru.nolimits.alexander.blockermaskcaller.repository.MasksRepository

@AndroidEntryPoint
class ListMasksViewModel(private val repository: MasksRepository) : ViewModel() {

    val allMasks: LiveData<List<Mask>> = repository.allMasks.asLiveData()

    init {
        Log.i("ListMasksViewModel", "ListMasksViewModel created!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ListMasksViewModel", "ListMasksViewModel destroyed!")
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            repository.delete(id)
        }
    }

}

class ListMasksViewModelFactory(private val repository: MasksRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListMasksViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ListMasksViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}