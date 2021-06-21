package ru.nolimits.alexander.blockermaskcaller.screens.fragments.masks.item

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.nolimits.alexander.blockermaskcaller.data.Mask
import ru.nolimits.alexander.blockermaskcaller.repository.MasksRepository


class ItemMaskViewModel(private val repository: MasksRepository) : ViewModel() {


    init {
        Log.i("NewMaskViewModel", "NewMaskViewModel created!")
    }

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

    suspend fun getMaskById(idMask: Int): Mask {
        return repository.getMaskById(idMask)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("NewMaskViewModel", "NewMaskViewModel destroyed!")
    }
}

class ItemMaskViewModelFactory(private val repository: MasksRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ItemMaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemMaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}