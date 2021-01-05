package ru.nolimits.alexander.blockermaskcaller.data.view.models

import androidx.lifecycle.*
import ru.nolimits.alexander.blockermaskcaller.database.Mask
import ru.nolimits.alexander.blockermaskcaller.repository.MasksRepository

class ListMasksViewModel(repository: MasksRepository) : ViewModel() {

    val allMasks: LiveData<List<Mask>> = repository.allMasks.asLiveData()

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