package ru.nolimits.alexander.blockermaskcaller.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "masks_table")
data class Mask(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val numeric: String,
    @ColumnInfo val title: String?
)