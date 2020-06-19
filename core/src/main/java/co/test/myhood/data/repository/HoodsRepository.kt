package co.test.myhood.data.repository

import co.test.myhood.data.dataSource.HoodsDataSource
import co.test.myhood.domain.Hood

class HoodsRepository constructor(
    private val remoteHoodsDataSource: HoodsDataSource,
    private val localHoodsDataSource: HoodsDataSource
) {

    suspend fun getHoodList(): List<Hood> {
        //TODO get it from API or DB?
        return remoteHoodsDataSource.getHoods()
    }
}