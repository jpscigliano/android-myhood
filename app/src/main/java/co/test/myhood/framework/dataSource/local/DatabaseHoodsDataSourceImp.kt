package co.test.myhood.framework.dataSource.local

import co.test.myhood.data.dataSource.LocalHoodsDataSource
import co.test.myhood.domain.Hood
import co.test.myhood.dto.database.DBHoodDTO
import co.test.myhood.framework.dataSource.local.db.HoodsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatabaseHoodsDataSourceImp @Inject constructor(
    private val hoodsDao: HoodsDao
) : LocalHoodsDataSource {

    override fun getHoods(): Flow<List<Hood>> {
        return hoodsDao.getAllFlow().map {
            it.map {
                //TODO user a mappe
                Hood(it.id, it.name)
            }
        }
    }

    override suspend fun saveHoods(list: List<Hood>) {
        val dbList = list.map {
            DBHoodDTO(it.id, it.name, "")
        }
        hoodsDao.delete()
        hoodsDao.saveAll(dbList)
    }
}