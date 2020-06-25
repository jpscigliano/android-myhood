package co.test.myhood.framework.dataSource.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import co.test.myhood.dto.database.DBHoodDTO

@Dao
interface HoodsDao {

    
    @Query("SELECT * FROM  DBHoodDTO")
    fun getAll(): List<DBHoodDTO>

    @Insert(onConflict = REPLACE)
    suspend fun saveAll(list: List<DBHoodDTO>)

    @Query("DELETE FROM DBHoodDTO")
    suspend fun delete()
}
