package ru.nolimits.alexander.blockermaskcaller.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMask(mask: Mask)

    @Query("DELETE FROM masks_table WHERE id = :idMask")
    fun deleteMask(idMask: Int)

    @Update
    fun updateMask(mask: Mask)

    @Query("SELECT * FROM masks_table")
    fun getAllMasks(): Flow<List<Mask>>

    @Query("DELETE FROM masks_table")
    fun deleteAll()

    @Query("SELECT * FROM masks_table WHERE id = :idMask")
    fun getMaskById(idMask: Int): Flow<Mask>

    @Query("SELECT * FROM masks_table WHERE numeric = :number")
    fun getMaskByNumeric(number: Int): Mask?
}