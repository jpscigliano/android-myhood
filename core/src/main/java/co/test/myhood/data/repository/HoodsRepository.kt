package co.test.myhood.data.repository

import co.test.myhood.data.Resource
import co.test.myhood.data.dataSource.LocalHoodsDataSource
import co.test.myhood.data.dataSource.RemoteHoodsDataSource
import co.test.myhood.data.networkBoundResource
import co.test.myhood.domain.Hood
import kotlinx.coroutines.flow.Flow

class HoodsRepository constructor(
    private val remoteHoodsDataSource: RemoteHoodsDataSource,
    private val localHoodsDataSource: LocalHoodsDataSource,
    private val locationHoodsRepository: LocationHoodsRepository
) {

    fun getHoods(): Flow<Resource<List<Hood>>> {
        return networkBoundResource(
            query = { localHoodsDataSource.getHoods() },
            fetch = {
                remoteHoodsDataSource.getHoods().map {
                    it.imageUrl=locationHoodsRepository.getHoodImageUrl(it.name)
                    it
                }
            },
            saveFetchResult = { items ->
                items.forEach {
                    println("LOG"+ it.imageUrl)
                }
                localHoodsDataSource.saveHoods(items)
            },
            shouldFetch = { true })
    }

    suspend fun getImageOfHoods(name: String): String {
        return remoteHoodsDataSource.getImageHood(name)
    }

    suspend fun forceHoodUpdate() {
        localHoodsDataSource.saveHoods(mutableListOf(Hood("Neuquen", "Neuquen")))
    }
}