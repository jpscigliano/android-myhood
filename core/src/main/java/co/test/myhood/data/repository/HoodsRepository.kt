package co.test.myhood.data.repository

import co.test.myhood.data.dataSource.LocalHoodsDataSource
import co.test.myhood.data.dataSource.RemoteHoodsDataSource
import co.test.myhood.data.networkBoundResource

import co.test.myhood.domain.Hood

class HoodsRepository constructor(
    private val remoteHoodsDataSource: RemoteHoodsDataSource,
    private val localHoodsDataSource: LocalHoodsDataSource
) {

    fun getHoods() = networkBoundResource(
        query = { localHoodsDataSource.getHoods() },
        fetch = { remoteHoodsDataSource.getHoods() },
        saveFetchResult = { items -> localHoodsDataSource.saveHoods(items) },
        shouldFetch = { true }
    )

    suspend fun forceHoodUpdate() {
        localHoodsDataSource.saveHoods(mutableListOf(Hood("Neuquen", "Neuquen")))
    }
}