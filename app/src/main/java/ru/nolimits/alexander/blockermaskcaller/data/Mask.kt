package ru.nolimits.alexander.blockermaskcaller.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "masks_table")
@Parcelize
data class Mask(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val numeric: String,
    @ColumnInfo val title: String?
) : Parcelable