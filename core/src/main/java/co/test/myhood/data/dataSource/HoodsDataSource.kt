package co.test.myhood.data.dataSource

import co.test.myhood.domain.Hood

interface HoodsDataSource {
    suspend fun getHoods():List<Hood>
}