package ru.nolimits.alexander.blockermaskcaller.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "masks_table")
data class Mask(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val numeric: String,
    @ColumnInfo val title: String?
)

@Dao
interface MaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMask(mask: Mask)

    @Delete
    fun deleteMask(mask: Mask)

    @Update
    fun updateMask(mask: Mask)

    @Query("SELECT * FROM masks_table")
    fun getAllMasks(): Flow<List<Mask>>

    @Query("DELETE FROM masks_table")
    fun deleteAll()

    @Query("SELECT * FROM masks_table WHERE id = :idMask")
    fun getMaskById(idMask: Int): Flow<Mask>
}