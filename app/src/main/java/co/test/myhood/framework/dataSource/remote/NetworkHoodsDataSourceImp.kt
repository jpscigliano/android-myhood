package co.test.myhood.framework.dataSource.remote

import co.test.myhood.data.dataSource.HoodsDataSource
import co.test.myhood.domain.Hood
import javax.inject.Inject

class NetworkHoodsDataSourceImp @Inject constructor() : HoodsDataSource {
    override fun getHoods(): List<Hood> {
        return mutableListOf<Hood>(Hood("1", "Remote_1"), Hood("2", "Remote_2"))
    }
}