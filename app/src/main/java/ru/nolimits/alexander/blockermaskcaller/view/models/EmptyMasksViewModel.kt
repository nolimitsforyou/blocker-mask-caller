package ru.nolimits.alexander.blockermaskcaller.view.models

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.nolimits.alexander.blockermaskcaller.data.MasksRepository
import javax.inject.Inject

@HiltViewModel
class EmptyMasksViewModel @Inject constructor(private val repository: MasksRepository) :
    ViewModel() {

}