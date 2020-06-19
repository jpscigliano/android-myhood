package co.test.myhood.framework.dataSource.local

import co.test.myhood.data.dataSource.HoodsDataSource
import co.test.myhood.domain.Hood
import javax.inject.Inject

class DatabaseHoodsDataSourceImp @Inject constructor() : HoodsDataSource {
    override suspend  fun getHoods(): List<Hood> {
        return mutableListOf<Hood>(Hood("1", "Local_1"), Hood("2", "Local_2"))
    }
}