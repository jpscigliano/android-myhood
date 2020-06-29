package co.test.myhood.framework.dataSource.local

import co.test.myhood.data.dataSource.LocalHoodsDataSource
import co.test.myhood.domain.Hood
import co.test.myhood.dto.database.DBHoodDTO
import co.test.myhood.framework.dataSource.local.db.HoodsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DatabaseHoodsDataSourceImp @Inject constructor(
    private val hoodsDao: HoodsDao
) : LocalHoodsDataSource {

    override fun getHoods(): Flow<List<Hood>> = flow {
        emit(hoodsDao.getAll().map {
            Hood(it.id, it.name,it.imageURL)
        })
    }.flowOn(Dispatchers.IO)

    override suspend fun saveHoods(list: List<Hood>) {
        val dbList = list.map {
            DBHoodDTO(it.id, it.name, it.imageUrl?:"")
        }
        hoodsDao.delete()
        hoodsDao.saveAll(dbList)
    }
}