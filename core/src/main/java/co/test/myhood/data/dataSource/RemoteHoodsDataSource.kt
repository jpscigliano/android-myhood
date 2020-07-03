package co.test.myhood.data.dataSource

import co.test.myhood.domain.Hood

interface RemoteHoodsDataSource {
    suspend fun getHoods(): List<Hood>
}