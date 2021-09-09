package ru.nolimits.alexander.blockermaskcaller.view.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.nolimits.alexander.blockermaskcaller.data.Mask
import ru.nolimits.alexander.blockermaskcaller.data.MasksRepository
import javax.inject.Inject

@HiltViewModel
class ItemMaskViewModel @Inject constructor(private val repository: MasksRepository) : ViewModel() {

    fun insert(mask: Mask) {
        viewModelScope.launch {
            repository.insert(mask)
        }
    }

    fun update(mask: Mask) {
        viewModelScope.launch {
            repository.update(mask)
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            repository.delete(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("NewMaskViewModel", "NewMaskViewModel destroyed!")
    }
}