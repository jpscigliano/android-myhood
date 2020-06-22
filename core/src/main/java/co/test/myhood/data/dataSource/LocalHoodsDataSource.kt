package co.test.myhood.data.dataSource

import co.test.myhood.domain.Hood
import kotlinx.coroutines.flow.Flow

interface LocalHoodsDataSource {
    fun getHoods(): Flow<List<Hood>>
    suspend fun saveHoods(list: List<Hood>) {}
}