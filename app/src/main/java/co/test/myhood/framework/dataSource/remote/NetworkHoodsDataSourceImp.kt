package co.test.myhood.framework.dataSource.remote

import co.test.myhood.data.dataSource.RemoteHoodsDataSource
import co.test.myhood.domain.Hood
import co.test.myhood.framework.dataSource.remote.api.HoodsAPI
import javax.inject.Inject

class NetworkHoodsDataSourceImp @Inject constructor(
    private val hoodsAPI: HoodsAPI
) : RemoteHoodsDataSource {

    override suspend fun getHoods(): List<Hood> {
        return hoodsAPI.fetchHoods().map {
            //Todo use a mapper
            Hood(it, it)
        }
    }
}