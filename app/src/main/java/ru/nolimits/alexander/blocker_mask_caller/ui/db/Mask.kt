package ru.nolimits.alexander.blocker_mask_caller.ui.db

import androidx.room.*

@Entity(tableName = "masks")
data class Mask(
    @PrimaryKey val id: Int,
    @ColumnInfo val numeric: String?,
    @ColumnInfo val title: String?
)

interface MasksDao {

    @Insert
    fun insertNewMask(mask: Mask)

    @Delete
    fun deleteMask(mask: Mask)

    @Update
    fun updateMask(mask: Mask)

    @Query("SELECT * FROM masks")
    fun getAllMasks(): List<Mask>
}