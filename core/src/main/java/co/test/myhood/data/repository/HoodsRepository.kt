package co.test.myhood.data.repository

import co.test.myhood.data.Resource
import co.test.myhood.data.dataSource.LocalHoodsDataSource
import co.test.myhood.data.dataSource.RemoteHoodsDataSource
import co.test.myhood.data.networkBoundResource
import co.test.myhood.domain.Hood
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow

class HoodsRepository constructor(
    private val remoteHoodsDataSource: RemoteHoodsDataSource,
    private val localHoodsDataSource: LocalHoodsDataSource,
    private val locationImageHoodsRepository: LocationImageHoodsRepository
) {

    fun getHoods(): Flow<Resource<List<Hood>>> {
        return networkBoundResource(
            query = { localHoodsDataSource.getHoods() },
            fetch = {
                coroutineScope {
                    remoteHoodsDataSource.getHoods().map {
                        async(Dispatchers.IO) {
                            it.imageUrl = locationImageHoodsRepository.getImageByLocationName(it.name)
                            it
                        }
                    }.map {
                        it.await()
                    }
                }
            },
            saveFetchResult = { items ->
                localHoodsDataSource.saveHoods(items)
            },
            shouldFetch = { true })
    }

    suspend fun forceHoodUpdate() {
        localHoodsDataSource.saveHoods(mutableListOf(Hood("Neuquen", "Neuquen")))
    }
}