package co.test.myhood.framework.dataSource.remote

import co.test.myhood.data.dataSource.HoodsDataSource
import co.test.myhood.domain.Hood
import co.test.myhood.framework.dataSource.remote.api.HoodsAPI
import javax.inject.Inject

class NetworkHoodsDataSourceImp @Inject constructor(
    private val hoodsAPI: HoodsAPI
) : HoodsDataSource {

    override suspend fun getHoods(): List<Hood> {
        return hoodsAPI.fetchHoods().map {
            Hood(it, it)
        }
    }
}