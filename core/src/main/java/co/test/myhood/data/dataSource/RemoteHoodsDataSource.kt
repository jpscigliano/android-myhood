package co.test.myhood.data.dataSource

import co.test.myhood.domain.Hood
import kotlinx.coroutines.flow.Flow

interface RemoteHoodsDataSource {
    suspend fun getHoods(): List<Hood>
    suspend fun getImageHood(name:String):String
}