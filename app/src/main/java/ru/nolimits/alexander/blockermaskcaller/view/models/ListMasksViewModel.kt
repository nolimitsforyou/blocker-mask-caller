package ru.nolimits.alexander.blockermaskcaller.view.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.nolimits.alexander.blockermaskcaller.data.Mask
import ru.nolimits.alexander.blockermaskcaller.data.MasksRepository
import javax.inject.Inject

@HiltViewModel
class ListMasksViewModel @Inject constructor(private val repository: MasksRepository) :
    ViewModel() {

    val allMasks: LiveData<List<Mask>> = repository.allMasks.asLiveData()

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