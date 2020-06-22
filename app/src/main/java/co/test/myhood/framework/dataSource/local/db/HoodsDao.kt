package co.test.myhood.framework.dataSource.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import co.test.myhood.dto.database.DBHoodDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface HoodsDao {


    @Query("SELECT * FROM  DBHoodDTO")
    suspend fun getAll(): List<DBHoodDTO>

    @Query("SELECT * FROM  DBHoodDTO")
    fun getAllFlow(): Flow<List<DBHoodDTO>>

    @Insert(onConflict = REPLACE)
    suspend fun saveAll(list: List<DBHoodDTO>)

    @Query( "DELETE FROM DBHoodDTO")
    suspend fun delete()
}
