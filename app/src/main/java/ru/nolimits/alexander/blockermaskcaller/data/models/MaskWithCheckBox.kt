package ru.nolimits.alexander.blockermaskcaller.data.models

data class MaskWithCheckBox(
    val id: Int,
    val numeric: String,
    val title: String?,
    val isChecked: Boolean
)