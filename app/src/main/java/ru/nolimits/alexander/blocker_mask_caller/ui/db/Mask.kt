package ru.nolimits.alexander.blocker_mask_caller.ui.db

import androidx.room.*

@Entity(tableName = "masks_table")
data class Mask(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val numeric: String?,
    @ColumnInfo val title: String?
)

@Dao
interface MasksDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNewMask(mask: Mask)

    @Delete
    fun deleteMask(mask: Mask)

    @Update
    fun updateMask(mask: Mask)

    @Query("SELECT * FROM masks_table")
    fun getAllMasks(): List<Mask>
}